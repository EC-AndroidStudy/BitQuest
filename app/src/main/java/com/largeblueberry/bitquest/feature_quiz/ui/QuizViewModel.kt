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
import com.largeblueberry.bitquest.feature_quiz.ui.model.QuizUiState
import com.largeblueberry.bitquest.ui.navigation.NavArgumentKeys
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
    private val uiMapper: QuizUiMapper,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<QuizScreenState>(QuizScreenState.Loading)
    val uiState: StateFlow<QuizScreenState> = _uiState.asStateFlow()
    val category: String? = savedStateHandle.get<String>("category")

    // 2. ViewModel 초기화 시 퀴즈 로드 로직 추가
    init {
        Log.d("QuizViewModel", "Category received: $category")
        // category가 있다면 해당 카테고리로 로드
        category?.let {
            loadQuizzesByCategory(it)
        } ?: run {
            // category가 없다면 (예: 퀴즈 메인 화면) 전체 퀴즈를 로드하거나,
            // 또는 에러 상태로 처리할 수 있습니다. 여기서는 전체 로드를 선택합니다.
            loadAllQuizzes()
        }
    }


    fun loadAllQuizzes() {
        loadQuizzesWith {
            uiMapper.mapToUiModelList(getAllQuizzesUseCase())
        }
    }

    fun loadQuizById(id: Int) {
        loadQuizzesWith {
            val domainQuiz = getQuizByIdUseCase(id)
            if (domainQuiz != null) {
                // 헬퍼 함수가 List를 기대하므로, 단일 객체를 리스트로 감싸서 반환
                uiMapper.mapToUiModelList(listOf(domainQuiz))
            } else {
                // 퀴즈를 찾지 못한 경우 빈 리스트를 반환하거나 예외를 던질 수 있습니다.
                // 여기서는 예외를 던져 Error 상태로 처리되도록 합니다.
                throw NoSuchElementException("ID가 $id 인 퀴즈를 찾을 수 없습니다.")
            }
        }
    }

    fun loadQuizzesByCategory(category: String) {
        loadQuizzesWith {
            val domainQuizzes = getQuizzesByCategoryUseCase(category)
            uiMapper.mapToUiModelList(domainQuizzes)
        }
    }

    fun loadQuizzesByType(type: QuizTypeUi) {
        loadQuizzesWith {
            val domainType = uiMapper.mapQuizTypeToDomain(type)
            val domainQuizzes = getQuizzesByTypeUseCase(domainType)
            uiMapper.mapToUiModelList(domainQuizzes)
        }
    }


    fun selectAnswer(answerIndex: Int) {
        updateContentState { it.copy(selectedAnswer = answerIndex) }
    }

    fun submitAnswer() {
        updateContentState { currentState ->
            val quiz = currentState.currentQuiz ?: return@updateContentState currentState
            val answer = currentState.selectedAnswer ?: return@updateContentState currentState

            val domainQuiz = uiMapper.mapToDomain(quiz)
            val domainResult = checkAnswerUseCase(domainQuiz, answer)
            val uiResult = uiMapper.mapResultToUiModel(domainResult)

            currentState.copy(
                quizResult = uiResult,
                showResult = true
            )
        }
    }

    fun nextQuiz() {
        updateContentState { currentState ->
            val nextIndex = currentState.currentQuizIndex + 1
            // 다음 퀴즈가 있을 경우
            if (nextIndex < currentState.quizzes.size) {
                currentState.copy(
                    currentQuizIndex = nextIndex,
                    selectedAnswer = null,
                    showResult = false,
                    quizResult = null
                )
            } else {
                // 다음 퀴즈가 없을 경우 (퀴즈 종료)
                currentState.copy(isQuizFinished = true)
            }
        }
    }

    fun restartQuiz() {
        updateContentState { currentState ->
            currentState.copy(
                currentQuizIndex = 0,
                selectedAnswer = null,
                showResult = false,
                quizResult = null,
                isQuizFinished = false // 종료 상태를 다시 false로
            )
        }
    }

    fun previousQuiz() {
        updateContentState { currentState ->
            val prevIndex = currentState.currentQuizIndex - 1
            if (prevIndex >= 0) {
                currentState.copy(
                    currentQuizIndex = prevIndex,
                    selectedAnswer = null,
                    showResult = false,
                    quizResult = null
                )
            } else {
                currentState // 변경 없음
            }
        }
    }

    fun resetQuiz() {
        updateContentState { it.copy(
            selectedAnswer = null,
            showResult = false,
            quizResult = null
        )}
    }


    private fun loadQuizzesWith(block: suspend () -> List<QuizUiModel>) {
        viewModelScope.launch {
            _uiState.value = QuizScreenState.Loading
            try {
                val uiQuizzes = block()
                _uiState.value = QuizScreenState.Success(
                    QuizContentState(quizzes = uiQuizzes)
                )
            } catch (e: Exception) {
                _uiState.value = QuizScreenState.Error(e.message ?: "알 수 없는 오류가 발생했습니다.")
            }
        }
    }

    /**
     * 현재 상태가 Success일 때만 안전하게 contentState를 업데이트하는 헬퍼 함수.
     * @param block: 현재 QuizContentState를 받아 새로운 QuizContentState를 반환하는 람다.
     */
    private fun updateContentState(block: (QuizContentState) -> QuizContentState) {
        val currentState = _uiState.value
        if (currentState is QuizScreenState.Success) {
            val newContentState = block(currentState.content)
            _uiState.value = currentState.copy(content = newContentState)
        }
    }
}