// mapper/QuizMapper.kt
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
        type = QuizType.valueOf(type) // 또는 적절한 변환 로직
    )
}

fun Quiz.toQuizEntity(): QuizEntity {
    return QuizEntity(
        id = id,
        title = title,
        question = question,
        options = options,
        correctAnswer = correctAnswer,
        explanation = explanation,
        category = category,
        type = type.name // 또는 type.toString()
    )
}
