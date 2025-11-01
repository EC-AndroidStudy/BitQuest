package com.largeblueberry.bitquest.feature_gemini

data class WrongAnswer(
    val questionId: String,
    val question: String,
    val userAnswer: String,
    val correctAnswer: String,
    val category: String,
    val difficulty: String,
    val timestamp: Long = System.currentTimeMillis()
)

