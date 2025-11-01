package com.largeblueberry.bitquest.feature_quiz.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
private fun QuizEndScreen(
    onNavigateHome: () -> Unit,
    onRestartQuiz: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "모든 퀴즈를 완료했습니다!", style = MaterialTheme.typography.headlineSmall )
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onNavigateHome, modifier = Modifier.fillMaxWidth()) {
            Text("홈으로 돌아가기")
        }
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(onClick = onRestartQuiz, modifier = Modifier.fillMaxWidth()) {
            Text("다시 풀기")
        }
    }
}