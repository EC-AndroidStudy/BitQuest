package com.largeblueberry.bitquest.feature_quiz.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.largeblueberry.bitquest.feature_quiz.ui.QuizViewModel
import com.largeblueberry.bitquest.feature_quiz.ui.model.QuizUiModel

@Composable
fun ChoicesBlock(
    quiz: QuizUiModel,
    viewModel: QuizViewModel,
    selectedAnswer: Int?,
    showResult: Boolean
) {
    Column {
        quiz.options.forEachIndexed { index, option ->
            Button(
                onClick = { viewModel.selectAnswer(index) }, // 인덱스를 전달
                modifier = Modifier.fillMaxWidth(),
                enabled = !showResult
            ) {
                Text(text = option)
            }
            if (index < quiz.options.size - 1) {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}