package com.largeblueberry.bitquest.feature_quiz.domain.model

data class Quiz(
    val id: Int,
    val title: String, // 누락되었던 title 필드 추가
    val question: String,
    val options: List<String>,
    val correctAnswer: Int,
    val explanation: String,
    val category: String,
    val type: QuizType
)