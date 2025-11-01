package com.largeblueberry.bitquest.feature_quiz.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.largeblueberry.bitquest.feature_quiz.ui.components.ChoicesBlock
import com.largeblueberry.bitquest.feature_quiz.ui.components.ExplanationBlock
import com.largeblueberry.bitquest.feature_quiz.ui.components.ProblemBlock
import com.largeblueberry.bitquest.feature_quiz.ui.components.ResultBlock

@Composable
fun QuizDetailScreen(
    viewModel: QuizViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val currentQuiz = uiState.currentQuiz

    if (uiState.isLoading || currentQuiz == null) {
        LoadingScreen()
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            ProblemBlock(quiz = currentQuiz)
            Spacer(modifier = Modifier.height(24.dp))

            uiState.quizResult?.takeIf { uiState.showResult }?.let { quizResult ->
                ExplanationBlock(text = currentQuiz.explanation)
                Spacer(modifier = Modifier.height(16.dp))
                ResultBlock(
                    isCorrect = quizResult.isCorrect,
                    message = quizResult.resultMessage
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            ChoicesBlock(
                quiz = currentQuiz,
                viewModel = viewModel,
                selectedAnswer = uiState.selectedAnswer,
                showResult = uiState.showResult
            )
        }
    }
}

@Composable
private fun LoadingScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "퀴즈를 불러오는 중...")
    }
}