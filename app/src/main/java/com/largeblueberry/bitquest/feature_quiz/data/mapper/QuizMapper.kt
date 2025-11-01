package com.largeblueberry.bitquest.feature_quiz.data.mapper

import com.largeblueberry.bitquest.feature_quiz.data.QuizEntity
import com.largeblueberry.bitquest.feature_quiz.domain.model.Quiz
import com.largeblueberry.bitquest.feature_quiz.domain.model.QuizType

fun QuizEntity.toQuiz(): Quiz {
    return Quiz(
        id = id,
        title = title,
        question = question,
        options = options,
        correctAnswer = correctAnswer,
        explanation = explanation,
        category = category,
        type = when (type) {
            "MULTIPLE_CHOICE" -> QuizType.MULTIPLE_CHOICE
            "OX" -> QuizType.OX
            else -> QuizType.MULTIPLE_CHOICE
        }
    )
}