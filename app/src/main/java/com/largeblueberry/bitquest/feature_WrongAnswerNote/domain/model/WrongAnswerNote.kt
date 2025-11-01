package com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.model

data class WrongAnswerNote(
    val id: Int? = null,
    val questionId: Int,
    val title: String, // 제목 필드 추가
    val questionText: String,
    val selectedAnswer: String,
    val correctAnswer: String,
    val category: String,
    val createdAt: Long = System.currentTimeMillis()
)