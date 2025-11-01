package com.largeblueberry.bitquest.feature_quiz.ui

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.largeblueberry.bitquest.feature_quiz.domain.usecase.CheckAnswerUseCase
import com.largeblueberry.bitquest.feature_quiz.domain.usecase.GetAllQuizzesUseCase
import com.largeblueberry.bitquest.feature_quiz.domain.usecase.GetQuizByIdUseCase
import com.largeblueberry.bitquest.feature_quiz.domain.usecase.GetQuizzesByCategoryUseCase
import com.largeblueberry.bitquest.feature_quiz.domain.usecase.GetQuizzesByTypeUseCase
import com.largeblueberry.bitquest.feature_quiz.ui.mapper.QuizUiMapper
import com.largeblueberry.bitquest.feature_quiz.ui.model.QuizContentState
import com.largeblueberry.bitquest.feature_quiz.ui.model.QuizScreenState
import com.largeblueberry.bitquest.feature_quiz.ui.model.QuizTypeUi
import com.largeblueberry.bitquest.feature_quiz.ui.model.QuizUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val getAllQuizzesUseCase: GetAllQuizzesUseCase,
    private val getQuizByIdUseCase: GetQuizByIdUseCase,
    private val checkAnswerUseCase: CheckAnswerUseCase,
    private val getQuizzesByCategoryUseCase: GetQuizzesByCategoryUseCase,
    private val getQuizzesByTypeUseCase: GetQuizzesByTypeUseCase,
    private val uiMapper: QuizUiMapper,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<QuizScreenState>(QuizScreenState.Loading)
    val uiState: StateFlow<QuizScreenState> = _uiState.asStateFlow()
    val category: String? = savedStateHandle.get<String>("category")

    init {
        Log.d("QuizViewModel", "Category received: $category")
        category?.let {
            loadQuizzesByCategory(it)
        } ?: run {
            loadAllQuizzes()
        }
    }

    fun loadAllQuizzes() = loadQuizzesWith { uiMapper.mapToUiModelList(getAllQuizzesUseCase()) }
    fun loadQuizById(id: Int) = loadQuizzesWith {
        getQuizByIdUseCase(id)?.let { uiMapper.mapToUiModelList(listOf(it)) }
            ?: throw NoSuchElementException("ID가 $id 인 퀴즈를 찾을 수 없습니다.")
    }
    fun loadQuizzesByCategory(category: String) = loadQuizzesWith { uiMapper.mapToUiModelList(getQuizzesByCategoryUseCase(category)) }
    fun loadQuizzesByType(type: QuizTypeUi) = loadQuizzesWith {
        val domainType = uiMapper.mapQuizTypeToDomain(type)
        uiMapper.mapToUiModelList(getQuizzesByTypeUseCase(domainType))
    }

    fun selectAnswer(answerIndex: Int) {
        updateSuccessState { it.copy(selectedAnswer = answerIndex) }
    }

    // checkAnswer 함수는 삭제하고, 이 함수를 사용합니다.
    fun submitAnswer() {
        updateSuccessState { content ->
            val quiz = content.currentQuiz ?: return@updateSuccessState content
            val answer = content.selectedAnswer ?: return@updateSuccessState content

            val domainQuiz = uiMapper.mapToDomain(quiz)
            val domainResult = checkAnswerUseCase(domainQuiz, answer)
            val uiResult = uiMapper.mapResultToUiModel(domainResult)

            content.copy(
                quizResult = uiResult,
                showResult = true
            )
        }
    }

    fun nextQuiz() {
        updateSuccessState { content ->
            val nextIndex = content.currentQuizIndex + 1
            if (nextIndex < content.quizzes.size) {
                content.copy(
                    currentQuizIndex = nextIndex,
                    selectedAnswer = null,
                    showResult = false,
                    quizResult = null
                )
            } else {
                content.copy(isQuizFinished = true)
            }
        }
    }

    fun restartQuiz() {
        updateSuccessState {
            it.copy(
                currentQuizIndex = 0,
                selectedAnswer = null,
                showResult = false,
                quizResult = null,
                isQuizFinished = false
            )
        }
    }

    fun previousQuiz() {
        updateSuccessState { content ->
            val prevIndex = content.currentQuizIndex - 1
            if (prevIndex >= 0) {
                content.copy(
                    currentQuizIndex = prevIndex,
                    selectedAnswer = null,
                    showResult = false,
                    quizResult = null
                )
            } else {
                content
            }
        }
    }

    private fun loadQuizzesWith(block: suspend () -> List<QuizUiModel>) {
        viewModelScope.launch {
            _uiState.value = QuizScreenState.Loading
            try {
                val uiQuizzes = block()
                _uiState.value = QuizScreenState.Success(QuizContentState(quizzes = uiQuizzes))
            } catch (e: Exception) {
                _uiState.value = QuizScreenState.Error(e.message ?: "알 수 없는 오류가 발생했습니다.")
            }
        }
    }

    // 더 명확한 이름의 새로운 헬퍼 함수
    private fun updateSuccessState(block: (QuizContentState) -> QuizContentState) {
        _uiState.update { currentState ->
            if (currentState is QuizScreenState.Success) {
                currentState.copy(content = block(currentState.content))
            } else {
                currentState
            }
        }
    }
}