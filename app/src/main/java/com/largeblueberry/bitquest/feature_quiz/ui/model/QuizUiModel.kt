package com.largeblueberry.bitquest.feature_quiz.ui.model

import com.largeblueberry.bitquest.feature_quiz.domain.model.QuizType

data class QuizUiModel(
    val id: Int,
    val title: String,
    val question: String,
    val options: List<String>,
    val correctAnswer: Int,
    val explanation: String,
    val category: String,
    val type: QuizTypeUi // 이전 수정에서 QuizTypeUi로 변경되었어야 함
) {
    // type 값에 따라 화면에 표시될 문자열을 반환하는 계산 프로퍼티
    val displayType: String
        get() = when (type) {
            QuizTypeUi.OX -> "O/X 퀴즈"
            QuizTypeUi.MULTIPLE_CHOICE -> "객관식"
        }
}