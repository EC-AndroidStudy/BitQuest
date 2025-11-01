package com.largeblueberry.bitquest.feature_quiz.ui.model

sealed interface QuizScreenState {
    // 퀴즈 목록을 불러오는 중
    object Loading : QuizScreenState

    // 퀴즈 목록 로딩 성공. 실제 콘텐츠 상태를 가짐
    data class Success(val content: QuizContentState) : QuizScreenState

    // 퀴즈 목록 로딩 실패
    data class Error(val message: String) : QuizScreenState
}