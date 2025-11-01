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
import com.largeblueberry.bitquest.feature_quiz.ui.components.ExplanationBlock
import com.largeblueberry.bitquest.feature_quiz.ui.components.ProblemBlock
import com.largeblueberry.bitquest.feature_quiz.ui.components.ResultBlock
import com.largeblueberry.bitquest.feature_quiz.ui.model.QuizContentState
import com.largeblueberry.bitquest.feature_quiz.ui.model.QuizScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizDetailScreen(
    navController: NavController,
    category: String? = null,
    quizType: String? = null,  // 추가 파라미터
    ids: String? = null,       // 추가 파라미터
    viewModel: QuizViewModel = hiltViewModel()
) {
    // 1. ViewModel의 상태를 구독합니다.
    val screenState by viewModel.uiState.collectAsState()

    LaunchedEffect(category, quizType, ids) {
        when {
            !category.isNullOrEmpty() -> {
                viewModel.loadQuizzes("CATEGORY", category)
            }
            !quizType.isNullOrEmpty() && !ids.isNullOrEmpty() -> {
                viewModel.loadQuizzes(quizType, ids)
            }
            else -> {
                // 기본값 또는 에러 처리
                viewModel.loadQuizzes("CATEGORY", "ANDROID")
            }
        }
    }

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
    // 5. 퀴즈가 종료되었는지 먼저 확인합니다.
    if (contentState.isQuizFinished) {
        // 종료 상태라면, 퀴즈 종료 화면을 보여줍니다.
        QuizEndScreen(
            onNavigateHome = onNavigateHome,
            onRestartQuiz = { viewModel.restartQuiz() }
        )
    } else {
        // 퀴즈가 진행 중이라면, 현재 퀴즈 문제를 표시합니다.
        val currentQuiz = contentState.currentQuiz

        if (currentQuiz == null) {
            // 퀴즈 목록은 불러왔지만 비어있는 예외적인 경우
            Text(text = "표시할 퀴즈가 없습니다.")
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                ProblemBlock(quiz = currentQuiz)
                Spacer(modifier = Modifier.height(24.dp))

                // 정답을 제출했을 때만 해설과 결과 블록을 표시
                contentState.quizResult?.takeIf { contentState.showResult }?.let { quizResult ->
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
                    selectedAnswer = contentState.selectedAnswer,
                    showResult = contentState.showResult
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 이전/다음 문제로 이동하는 버튼
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = { viewModel.previousQuiz() },
                        enabled = contentState.currentQuizIndex > 0 // 첫 문제에서는 비활성화
                    ) {
                        Text("이전")
                    }
                    Button(onClick = { viewModel.nextQuiz() }) {
                        // 마지막 문제일 경우 버튼 텍스트를 변경하여 사용자에게 알려줌
                        val buttonText = if (contentState.currentQuizIndex == contentState.quizzes.size - 1) {
                            "결과 보기"
                        } else {
                            "다음"
                        }
                        Text(buttonText)
                    }
                }
            }
        }
    }
}

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

@Composable
private fun ErrorScreen(message: String) {
    Text(text = "오류가 발생했습니다: $message", color = MaterialTheme.colorScheme.error)
}