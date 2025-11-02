package com.largeblueberry.bitquest.feature_quiz.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ErrorScreen(message: String) {
    Text(text = "오류가 발생했습니다: $message", color = MaterialTheme.colorScheme.error)
}