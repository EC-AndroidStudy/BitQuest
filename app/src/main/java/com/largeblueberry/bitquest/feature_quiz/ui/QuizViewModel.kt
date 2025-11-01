package com.largeblueberry.bitquest.feature_quiz.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.largeblueberry.bitquest.feature_quiz.domain.model.Quiz
import com.largeblueberry.bitquest.feature_quiz.domain.usecase.GetQuizzesByCategoryUseCase
import com.largeblueberry.bitquest.feature_quiz.domain.usecase.GetQuizzesByIdsUseCase
import com.largeblueberry.bitquest.feature_quiz.ui.mapper.toUiModel
import com.largeblueberry.bitquest.feature_quiz.ui.mapper.toUiModelList
import com.largeblueberry.bitquest.feature_quiz.ui.model.QuizContentState
import com.largeblueberry.bitquest.feature_quiz.ui.model.QuizScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val getQuizzesByCategory: GetQuizzesByCategoryUseCase,
    private val getQuizzesByIds: GetQuizzesByIdsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<QuizScreenState>(QuizScreenState.Loading)
    val uiState: StateFlow<QuizScreenState> = _uiState.asStateFlow()

    private var originalQuizzes: List<Quiz> = emptyList()

    fun loadQuizzes(quizType: String, ids: String) {
        viewModelScope.launch {
            _uiState.value = QuizScreenState.Loading
            try {
                originalQuizzes = when (quizType) {
                    "CATEGORY" -> getQuizzesByCategory(ids)
                    "WRONG_ANSWERS" -> {
                        val idList = ids.split(",").mapNotNull { it.toIntOrNull() }
                        getQuizzesByIds(idList)
                    }
                    else -> emptyList()
                }

                if (originalQuizzes.isEmpty()) {
                    _uiState.value = QuizScreenState.Error("해당하는 퀴즈가 없습니다.")
                } else {
                    _uiState.value = QuizScreenState.Success(
                        QuizContentState(quizzes = originalQuizzes.toUiModelList()) // 확장 함수 사용
                    )
                }
            } catch (e: Exception) {
                _uiState.value = QuizScreenState.Error(e.message ?: "퀴즈를 불러오는 데 실패했습니다.")
            }
        }
    }

    fun selectAnswer(answerIndex: Int) {
        val currentState = _uiState.value
        if (currentState is QuizScreenState.Success) {
            _uiState.update {
                (it as QuizScreenState.Success).copy(
                    content = it.content.copy(selectedAnswer = answerIndex)
                )
            }
        }
    }

    fun submitAnswer() {
        // TODO: 정답 제출 및 결과 처리 로직 구현
    }

    fun nextQuiz() {
        val currentState = _uiState.value
        if (currentState is QuizScreenState.Success) {
            val newIndex = currentState.content.currentQuizIndex + 1
            if (newIndex < currentState.content.quizzes.size) {
                _uiState.update {
                    (it as QuizScreenState.Success).copy(
                        content = it.content.copy(
                            currentQuizIndex = newIndex,
                            selectedAnswer = null,
                            showResult = false
                        )
                    )
                }
            }
        }
    }

    fun previousQuiz() {
        val currentState = _uiState.value
        if (currentState is QuizScreenState.Success) {
            val newIndex = currentState.content.currentQuizIndex - 1
            if (newIndex >= 0) {
                _uiState.update {
                    (it as QuizScreenState.Success).copy(
                        content = it.content.copy(
                            currentQuizIndex = newIndex,
                            selectedAnswer = null,
                            showResult = false
                        )
                    )
                }
            }
        }
    }

    fun restartQuiz() {
        if (originalQuizzes.isNotEmpty()) {
            _uiState.value = QuizScreenState.Success(
                QuizContentState(quizzes = originalQuizzes.toUiModelList()) // 확장 함수 사용
            )
        }
    }
}