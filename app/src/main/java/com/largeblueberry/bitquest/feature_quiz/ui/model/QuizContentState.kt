package com.largeblueberry.bitquest.feature_quiz.ui.model

data class QuizContentState(
    val quizzes: List<QuizUiModel> = emptyList(),
    val currentQuizIndex: Int = 0,
    val selectedAnswer: Int? = null,
    val showResult: Boolean = false,
    val quizResult: QuizResultUiModel? = null,
    val isQuizFinished: Boolean = false
) {
    // 현재 퀴즈를 쉽게 가져오기 위한 편의 프로퍼티
    val currentQuiz: QuizUiModel?
        get() = quizzes.getOrNull(currentQuizIndex)
}
