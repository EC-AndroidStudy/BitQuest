package com.largeblueberry.bitquest.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
// import androidx.hilt.navigation.compose.hiltViewModel // <- 이 줄을 삭제하거나 주석 처리합니다.
import androidx.lifecycle.viewmodel.compose.viewModel // <- 이 줄을 추가합니다.
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.largeblueberry.bitquest.ui.theme.BitQuestTheme
import com.largeblueberry.bitquest.ui.screens.ResultUiState
import com.largeblueberry.bitquest.ui.screens.ResultViewModel

/**
 * [작업 담당자: Android 개발자]
 *
 * TODO: 퀴즈 결과 화면
 *
 * ResultScreen.kt:
 * - 정답/오답 결과 표시
 * - 점수 및 통계 시각화 (차트)
 * - 틀린 문제 리뷰 기능
 * - 소셜 공유 기능
 * - 다시 풀기, 다음 퀴즈 버튼
 */

// UI에서 발생하는 이벤트를 정의하는 클래스
sealed class ResultEvent {
    object OnRetryClick : ResultEvent()
    object OnShareClick : ResultEvent()
    object OnReviewClick : ResultEvent()
}

@Composable
fun ResultScreen(
    navController: NavController,
    // hiltViewModel() 대신 기본 viewModel() 함수를 사용합니다.
    viewModel: ResultViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    ResultScreenContent(
        uiState = uiState,
        onEvent = viewModel::onEvent,
        navController = navController
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreenContent(
    uiState: ResultUiState,
    onEvent: (ResultEvent) -> Unit,
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("퀴즈 결과") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ))
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            ResultSummarySection(uiState = uiState)
            ActionButtonsSection(
                onEvent = onEvent,
                navController = navController
            )
        }
    }
}

@Composable
private fun ResultSummarySection(uiState: ResultUiState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Text(text = "${uiState.totalQuestions}문제 중 ${uiState.correctAnswers}개 정답!", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(100.dp))

        Text(text = "${uiState.score}점", style = MaterialTheme.typography.displayLarge)
        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Composable
private fun ActionButtonsSection(
    onEvent: (ResultEvent) -> Unit,
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { onEvent(ResultEvent.OnReviewClick) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("틀린 문제 다시 보기")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    onEvent(ResultEvent.OnRetryClick)
                    navController.popBackStack()
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("다시 풀기")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = { onEvent(ResultEvent.OnShareClick) },
                modifier = Modifier.weight(1f)
            ) {
                Text("결과 공유")
            }
        }
    }
}

// --- Preview ---
@Preview(showBackground = true)
@Composable
private fun ResultScreenPreview() {
    BitQuestTheme {
        // Preview가 ViewModel에 직접 의존하지 않도록 Content 함수를 호출합니다.
        ResultScreenContent(
            uiState = ResultUiState(totalQuestions = 10, correctAnswers = 8, score = 80),
            onEvent = {},
            navController = rememberNavController()
        )
    }
}