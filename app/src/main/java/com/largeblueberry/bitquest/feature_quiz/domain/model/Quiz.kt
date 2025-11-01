package com.largeblueberry.bitquest.feature_quiz.domain.model


data class Quiz(
    val id: Int,
    val question: String,
    val options: List<String>,
    val correctAnswer: Int,
    val explanation: String,
    val category: String,
    val type: QuizType
)