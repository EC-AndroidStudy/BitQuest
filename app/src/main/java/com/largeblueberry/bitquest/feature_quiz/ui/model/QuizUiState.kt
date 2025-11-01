package com.largeblueberry.bitquest.feature_quiz.ui.model

data class QuizUiState(
    val currentQuiz: QuizUiModel? = null,
    val quizzes: List<QuizUiModel> = emptyList(),
    val currentQuizIndex: Int = 0,
    val selectedAnswer: Int? = null,
    val showResult: Boolean = false,
    val quizResult: QuizResultUiModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
