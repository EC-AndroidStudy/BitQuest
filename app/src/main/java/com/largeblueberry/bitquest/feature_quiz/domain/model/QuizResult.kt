package com.largeblueberry.bitquest.feature_quiz.domain.model

data class QuizResult(
    val quiz: Quiz,
    val selectedAnswer: Int,
    val isCorrect: Boolean,
    val timeSpent: Long = 0L
)