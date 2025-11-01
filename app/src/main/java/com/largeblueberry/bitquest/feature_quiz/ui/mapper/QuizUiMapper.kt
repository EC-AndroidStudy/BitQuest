package com.largeblueberry.bitquest.feature_quiz.ui.mapper

import com.largeblueberry.bitquest.feature_quiz.domain.model.Quiz
import com.largeblueberry.bitquest.feature_quiz.domain.model.QuizResult
import com.largeblueberry.bitquest.feature_quiz.domain.model.QuizType
import com.largeblueberry.bitquest.feature_quiz.ui.model.QuizResultUiModel
import com.largeblueberry.bitquest.feature_quiz.ui.model.QuizTypeUi
import com.largeblueberry.bitquest.feature_quiz.ui.model.QuizUiModel

// Domain -> UI
fun Quiz.toUiModel(): QuizUiModel = QuizUiModel(
    id = id,
    title = title,
    question = question,
    options = options,
    correctAnswer = correctAnswer,
    explanation = explanation,
    category = category,
    type = type.toUi()
)

// UI -> Domain
fun QuizUiModel.toDomain(): Quiz = Quiz(
    id = id,
    title = title,
    question = question,
    options = options,
    correctAnswer = correctAnswer,
    explanation = explanation,
    category = category,
    type = type.toDomain()
)

fun List<Quiz>.toUiModelList(): List<QuizUiModel> = this.map { it.toUiModel() }

fun QuizResult.toUiModel(): QuizResultUiModel = QuizResultUiModel(
    quiz = quiz.toUiModel(),
    selectedAnswer = selectedAnswer,
    isCorrect = isCorrect,
    timeSpent = timeSpent
)

private fun QuizType.toUi(): QuizTypeUi = when (this) {
    QuizType.OX -> QuizTypeUi.OX
    QuizType.MULTIPLE_CHOICE -> QuizTypeUi.MULTIPLE_CHOICE
}

private fun QuizTypeUi.toDomain(): QuizType = when (this) {
    QuizTypeUi.OX -> QuizType.OX
    QuizTypeUi.MULTIPLE_CHOICE -> QuizType.MULTIPLE_CHOICE
}
