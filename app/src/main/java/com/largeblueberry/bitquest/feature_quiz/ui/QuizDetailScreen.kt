package com.largeblueberry.bitquest.feature_quiz.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.largeblueberry.bitquest.feature_quiz.ui.components.ChoicesBlock
import com.largeblueberry.bitquest.feature_quiz.ui.components.ErrorScreen
import com.largeblueberry.bitquest.feature_quiz.ui.components.ExplanationBlock
import com.largeblueberry.bitquest.feature_quiz.ui.components.LoadingScreen
import com.largeblueberry.bitquest.feature_quiz.ui.components.ProblemBlock
import com.largeblueberry.bitquest.feature_quiz.ui.components.ResultBlock
import com.largeblueberry.bitquest.feature_quiz.ui.model.QuizContentState
import com.largeblueberry.bitquest.feature_quiz.ui.model.QuizScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizDetailScreen(
    navController: NavController,
    category: String? = null,
    viewModel: QuizViewModel = hiltViewModel()
) {
    // 1. ViewModel의 상태를 구독합니다.
    val screenState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    // 카테고리가 있으면 제목에 표시
                    val title = if (!category.isNullOrEmpty()) {
                        "$category 퀴즈"
                    } else {
                        "BitQuest 퀴즈"
                    }
                    Text(title)
                }
            )
        }
    ) { paddingValues ->
        // 3. 화면의 본문 영역입니다.
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // 4. screenState의 종류에 따라 적절한 UI를 표시합니다. (가장 중요!)
            when (val state = screenState) {
                is QuizScreenState.Loading -> LoadingScreen()
                is QuizScreenState.Error -> ErrorScreen(message = state.message)
                is QuizScreenState.Success -> {
                    // 성공 상태일 때만 실제 콘텐츠 UI를 렌더링합니다.
                    QuizSuccessContent(
                        contentState = state.content,
                        viewModel = viewModel,
                        // "홈으로 가기" 버튼이 눌렸을 때 실행할 동작을 전달합니다.
                        onNavigateHome = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}

@Composable
private fun QuizSuccessContent(
    contentState: QuizContentState,
    viewModel: QuizViewModel,
    onNavigateHome: () -> Unit
) {
    // ... (함수 상단은 동일) ...
    if (contentState.isQuizFinished) {
        // ...
    } else {
        val currentQuiz = contentState.currentQuiz
        if (currentQuiz == null) {
            // ...
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // ... (ProblemBlock, ChoicesBlock 등은 동일) ...
                ProblemBlock(quiz = currentQuiz)
                Spacer(modifier = Modifier.height(24.dp))

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {
                    if(contentState.showResult) {
                        contentState.quizResult?.let { quizResult ->
                            ExplanationBlock(text = currentQuiz.explanation)
                            Spacer(modifier = Modifier.height(16.dp))
                            ResultBlock(
                                isCorrect = quizResult.isCorrect,
                                message = quizResult.resultMessage
                            )
                        }
                    }
                    else {
                        ChoicesBlock(
                            quiz = currentQuiz,
                            viewModel = viewModel,
                            selectedAnswer = contentState.selectedAnswer,
                            showResult = false
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = { viewModel.previousQuiz() },
                        enabled = contentState.currentQuizIndex > 0 && !contentState.showResult
                    ) {
                        Text("이전")
                    }

                    Button(
                        onClick = {
                            if (contentState.showResult) {
                                viewModel.nextQuiz()
                            } else {
                                // --- 여기가 핵심 수정 부분입니다 ---
                                // 잘못된 UseCase 호출 대신 ViewModel의 submitAnswer()를 호출합니다.
                                viewModel.submitAnswer()
                            }
                        },
                        enabled = contentState.selectedAnswer != null
                    ) {
                        val buttonText = when {
                            contentState.showResult && contentState.currentQuizIndex < contentState.quizzes.size - 1 -> "다음 문제"
                            contentState.showResult && contentState.currentQuizIndex == contentState.quizzes.size - 1 -> "결과 보기"
                            else -> "정답 확인"
                        }
                        Text(buttonText)
                    }
                }
            }
        }
    }
}