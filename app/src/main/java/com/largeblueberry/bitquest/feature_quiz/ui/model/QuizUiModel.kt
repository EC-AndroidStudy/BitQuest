package com.largeblueberry.bitquest.feature_quiz.ui.model

import com.largeblueberry.bitquest.feature_quiz.domain.model.Quiz
import com.largeblueberry.bitquest.feature_quiz.domain.model.QuizResult

data class QuizUiModel(
    val currentQuiz: Quiz? = null,
    val quizzes: List<Quiz> = emptyList(),
    val currentQuizIndex: Int = 0,
    val selectedAnswer: Int? = null,
    val showResult: Boolean = false,
    val quizResult: QuizResult? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

