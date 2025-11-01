package com.largeblueberry.bitquest.feature_quiz.ui.model

import com.largeblueberry.bitquest.feature_quiz.domain.model.Quiz
import com.largeblueberry.bitquest.feature_quiz.domain.model.QuizType

// Domain -> UI
fun Quiz.toUiModel(): QuizUiModel {
    return QuizUiModel(
        id = id,
        title = title,
        question = question,
        options = options,
        correctAnswer = correctAnswer,
        explanation = explanation,
        category = category,
        type = type.toUi() // 변환 함수 사용
    )
}

// QuizType을 QuizTypeUi로 변환하는 private 확장 함수
private fun QuizType.toUi(): QuizTypeUi = when (this) {
    QuizType.OX -> QuizTypeUi.OX
    QuizType.MULTIPLE_CHOICE -> QuizTypeUi.MULTIPLE_CHOICE
}
