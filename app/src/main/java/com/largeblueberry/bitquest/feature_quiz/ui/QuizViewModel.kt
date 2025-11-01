package com.largeblueberry.bitquest.feature_quiz.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.largeblueberry.bitquest.feature_quiz.domain.usecase.CheckAnswerUseCase
import com.largeblueberry.bitquest.feature_quiz.domain.usecase.GetAllQuizzesUseCase
import com.largeblueberry.bitquest.feature_quiz.domain.usecase.GetQuizByIdUseCase
import com.largeblueberry.bitquest.feature_quiz.domain.usecase.GetQuizzesByCategoryUseCase
import com.largeblueberry.bitquest.feature_quiz.domain.usecase.GetQuizzesByTypeUseCase
import com.largeblueberry.bitquest.feature_quiz.ui.mapper.QuizUiMapper
import com.largeblueberry.bitquest.feature_quiz.ui.model.QuizTypeUi
import com.largeblueberry.bitquest.feature_quiz.ui.model.QuizUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val getAllQuizzesUseCase: GetAllQuizzesUseCase,
    private val getQuizByIdUseCase: GetQuizByIdUseCase,
    private val checkAnswerUseCase: CheckAnswerUseCase,
    private val getQuizzesByCategoryUseCase: GetQuizzesByCategoryUseCase,
    private val getQuizzesByTypeUseCase: GetQuizzesByTypeUseCase,
    private val uiMapper: QuizUiMapper
) : ViewModel() {

    private val _uiState = MutableStateFlow(QuizUiState())
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    init {
        loadAllQuizzes()
    }

    fun loadAllQuizzes() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val domainQuizzes = getAllQuizzesUseCase()
                val uiQuizzes = uiMapper.mapToUiModelList(domainQuizzes)
                _uiState.value = _uiState.value.copy(
                    quizzes = uiQuizzes,
                    currentQuiz = uiQuizzes.firstOrNull(),
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message,
                    isLoading = false
                )
            }
        }
    }

    fun loadQuizzesByCategory(category: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val domainQuizzes = getQuizzesByCategoryUseCase(category)
                val uiQuizzes = uiMapper.mapToUiModelList(domainQuizzes)
                _uiState.value = _uiState.value.copy(
                    quizzes = uiQuizzes,
                    currentQuiz = uiQuizzes.firstOrNull(),
                    currentQuizIndex = 0,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message,
                    isLoading = false
                )
            }
        }
    }

    fun loadQuizzesByType(type: QuizTypeUi) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val domainType = uiMapper.mapQuizTypeToDomain(type)
                val domainQuizzes = getQuizzesByTypeUseCase(domainType)
                val uiQuizzes = uiMapper.mapToUiModelList(domainQuizzes)
                _uiState.value = _uiState.value.copy(
                    quizzes = uiQuizzes,
                    currentQuiz = uiQuizzes.firstOrNull(),
                    currentQuizIndex = 0,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message,
                    isLoading = false
                )
            }
        }
    }

    fun selectAnswer(answerIndex: Int) {
        _uiState.value = _uiState.value.copy(selectedAnswer = answerIndex)
    }

    fun submitAnswer() {
        val currentState = _uiState.value
        val uiQuiz = currentState.currentQuiz ?: return
        val selectedAnswer = currentState.selectedAnswer ?: return

        val domainQuiz = uiMapper.mapToDomain(uiQuiz)
        val domainResult = checkAnswerUseCase(domainQuiz, selectedAnswer)
        val uiResult = uiMapper.mapResultToUiModel(domainResult)

        _uiState.value = _uiState.value.copy(
            quizResult = uiResult,
            showResult = true
        )
    }

    fun nextQuiz() {
        val currentState = _uiState.value
        val nextIndex = currentState.currentQuizIndex + 1

        if (nextIndex < currentState.quizzes.size) {
            _uiState.value = _uiState.value.copy(
                currentQuizIndex = nextIndex,
                currentQuiz = currentState.quizzes[nextIndex],
                selectedAnswer = null,
                showResult = false,
                quizResult = null
            )
        }
    }

    fun previousQuiz() {
        val currentState = _uiState.value
        val prevIndex = currentState.currentQuizIndex - 1

        if (prevIndex >= 0) {
            _uiState.value = _uiState.value.copy(
                currentQuizIndex = prevIndex,
                currentQuiz = currentState.quizzes[prevIndex],
                selectedAnswer = null,
                showResult = false,
                quizResult = null
            )
        }
    }

    fun resetQuiz() {
        _uiState.value = _uiState.value.copy(
            selectedAnswer = null,
            showResult = false,
            quizResult = null
        )
    }
}