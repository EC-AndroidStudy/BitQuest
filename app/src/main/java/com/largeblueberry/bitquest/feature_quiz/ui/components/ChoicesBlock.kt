package com.largeblueberry.bitquest.feature_quiz.ui.components

import androidx.compose.runtime.Composable
import com.largeblueberry.bitquest.feature_quiz.ui.QuizViewModel
import com.largeblueberry.bitquest.feature_quiz.ui.model.QuizTypeUi
import com.largeblueberry.bitquest.feature_quiz.ui.model.QuizUiModel

@Composable
fun ChoicesBlock(
    quiz: QuizUiModel,
    viewModel: QuizViewModel,
    selectedAnswer: Int?,
    showResult: Boolean
) {
    when (quiz.type) {
        QuizTypeUi.MULTIPLE_CHOICE -> {
            MultipleChoiceButtons(
                choices = quiz.options,
                selectedAnswer = selectedAnswer,
                correctAnswer = if (showResult) quiz.correctAnswer else null,
                onAnswerSelected = { index ->
                    viewModel.selectAnswer(index)
                },
                enabled = !showResult
            )
        }
        QuizTypeUi.OX -> {
            OxButtons(
                selectedAnswer = selectedAnswer,
                correctAnswer = if (showResult) quiz.correctAnswer else null,
                onAnswerSelected = { answer ->
                    viewModel.selectAnswer(if (answer) 0 else 1)
                },
                enabled = !showResult
            )
        }
    }
}
