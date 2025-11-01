package com.largeblueberry.bitquest.feature_quiz.ui.model

data class QuizUiModel(
    val id: Int,
    val question: String,
    val options: List<String>,
    val correctAnswer: Int,
    val explanation: String,
    val category: String,
    val type: QuizTypeUi,
    val displayType: String = when (type) {
        QuizTypeUi.OX -> "OX 퀴즈"
        QuizTypeUi.MULTIPLE_CHOICE -> "4지선다"
    }
)