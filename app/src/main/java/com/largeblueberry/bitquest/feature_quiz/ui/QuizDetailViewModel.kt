package com.largeblueberry.bitquest.feature_quiz.ui

import androidx.lifecycle.ViewModel
import com.largeblueberry.bitquest.feature_quiz.domain.MultipleChoiceQuiz
import com.largeblueberry.bitquest.feature_quiz.domain.OxQuiz
import com.largeblueberry.bitquest.feature_quiz.domain.QuizModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuizDetailViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(QuizDetailState())
    val uiState = _uiState.asStateFlow()

    // TODO: 실제 퀴즈 데이터를 불러오는 로직을 여기에 구현해야 합니다. (e.g., from a repository)
    // TODO: 사용자의 답변을 처리하는 함수를 구현해야 합니다. (e.g., selectAnswer(answer: Any))

}

// 화면의 모든 상태를 관리하는 데이터 클래스
data class QuizDetailState(
    val quiz: QuizModel? = null, // 현재 화면에 표시될 퀴즈 데이터 (OX 또는 선택형)
    val userAnswer: Any? = null, // 사용자가 선택한 답 (선택형: Int(인덱스), OX: Boolean)
    val isAnswerSubmitted: Boolean = false // 답안 제출 여부
) {
    // 사용자의 정답 여부를 계산하는 프로퍼티
    val isCorrect: Boolean?
        get() {
            if (!isAnswerSubmitted || quiz == null || userAnswer == null) {
                return null
            }

            return when (quiz) {
                is MultipleChoiceQuiz -> {
                    // 선택형 퀴즈: 사용자가 선택한 인덱스와 정답 인덱스를 비교
                    (userAnswer as? Int) == quiz.answerIndex
                }
                is OxQuiz -> {
                    // OX 퀴즈: 사용자가 선택한 Boolean 값과 정답을 비교
                    (userAnswer as? Boolean) == quiz.answer
                }
            }
        }
}
