package com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.model

data class WrongAnswerNote(
    val id: Int = 0,
    val questionId: Int,
    val questionText: String,
    val selectedAnswer: String,
    val correctAnswer: String,
    val category: String,
    val createdAt: Long = System.currentTimeMillis() // epoch millis
)