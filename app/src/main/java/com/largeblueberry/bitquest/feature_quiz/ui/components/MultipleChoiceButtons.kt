package com.largeblueberry.bitquest.feature_quiz.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MultipleChoiceButtons(
    choices: List<String>,
    selectedAnswer: Int?,
    correctAnswer: Int?,
    onAnswerSelected: (Int) -> Unit,
    enabled: Boolean
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        choices.forEachIndexed { index, text ->
            val buttonColor = getButtonColor(index, selectedAnswer, correctAnswer)

            Button(
                onClick = { onAnswerSelected(index) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                enabled = enabled,
                colors = buttonColor?.let {
                    ButtonDefaults.buttonColors(containerColor = it)
                } ?: ButtonDefaults.buttonColors()
            ) {
                Text(text = "${index + 1}. $text", modifier = Modifier.padding(8.dp))
            }
        }
    }
}

private fun getButtonColor(index: Int, selectedAnswer: Int?, correctAnswer: Int?): Color? {
    return when {
        correctAnswer == index -> Color(0xFF4CAF50) // 정답은 초록색
        correctAnswer != null && selectedAnswer == index -> Color(0xFFF44336) // 틀린 답은 빨간색
        selectedAnswer == index -> Color(0xFF2196F3) // 선택된 답은 파란색
        else -> null // 기본 색상
    }
}