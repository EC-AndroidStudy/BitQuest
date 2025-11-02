package com.largeblueberry.bitquest.feature_WrongAnswerNote.data

data class WrongAnswer(
    val quizId: String,
    val question: String,
    val userAnswer: String,
    val correctAnswer: String,
    val explanation: String,
    val category: String,
    val timestamp: Long = System.currentTimeMillis()
)
