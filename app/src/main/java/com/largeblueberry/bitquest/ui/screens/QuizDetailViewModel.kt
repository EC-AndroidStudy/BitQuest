package com.largeblueberry.bitquest.ui.screens

import androidx.lifecycle.ViewModel
import com.largeblueberry.bitquest.domain.model.Choice
import com.largeblueberry.bitquest.domain.model.Quiz
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuizDetailViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(QuizDetailState())
    val uiState = _uiState.asStateFlow()

    // init 블록과 목업 관련 코드를 모두 제거했습니다.
    // TODO: 실제 퀴즈 데이터를 불러오는 로직을 여기에 구현해야 합니다. (e.g., from a repository)

}

// 화면의 모든 상태를 관리하는 데이터 클래스
data class QuizDetailState(
    val quiz: Quiz? = null, // 현재 화면에 표시될 퀴즈 데이터, 로딩 전에는 null
    val userAnswer: Choice? = null, // 사용자가 선택한 답
    val isAnswerSubmitted: Boolean = false // 답안 제출 여부
) {
    // 사용자의 정답 여부를 계산하는 프로퍼티
    val isCorrect: Boolean?
        get() = if (isAnswerSubmitted) userAnswer?.id == quiz?.choices?.get(quiz.answerIndex)?.id else null
}