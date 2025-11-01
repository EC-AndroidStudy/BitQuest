package com.largeblueberry.bitquest.feature_quiz.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OxButtons(
    selectedAnswer: Int?,
    correctAnswer: Int?,
    onAnswerSelected: (Boolean) -> Unit,
    enabled: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // O 버튼 (정답 인덱스 0)
        val oButtonColor = getButtonColor(0, selectedAnswer, correctAnswer)

        Button(
            onClick = { onAnswerSelected(true) },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f),
            enabled = enabled,
            colors = oButtonColor?.let {
                ButtonDefaults.buttonColors(containerColor = it)
            } ?: ButtonDefaults.buttonColors()
        ) {
            Text(text = "O", fontSize = 48.sp)
        }

        // X 버튼 (정답 인덱스 1)
        val xButtonColor = getButtonColor(1, selectedAnswer, correctAnswer)

        Button(
            onClick = { onAnswerSelected(false) },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f),
            enabled = enabled,
            colors = xButtonColor?.let {
                ButtonDefaults.buttonColors(containerColor = it)
            } ?: ButtonDefaults.buttonColors()
        ) {
            Text(text = "X", fontSize = 48.sp)
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