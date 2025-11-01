package com.largeblueberry.bitquest.feature_quiz.ui.model

data class QuizResultUiModel(
    val quiz: QuizUiModel,
    val selectedAnswer: Int,
    val isCorrect: Boolean,
    val timeSpent: Long = 0L,
    val resultMessage: String = if (isCorrect) "정답입니다! 🎉" else "틀렸습니다. 😅"
)
