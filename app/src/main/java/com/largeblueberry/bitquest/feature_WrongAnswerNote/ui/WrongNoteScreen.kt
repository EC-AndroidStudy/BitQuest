package com.largeblueberry.bitquest.feature_WrongAnswerNote.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LibraryBooks
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.largeblueberry.bitquest.feature_WrongAnswerNote.data.WrongAnswer
import com.largeblueberry.bitquest.feature_main.util.BitQuestColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WrongNoteScreen(
    navController: NavController,
    viewModel: WrongAnswerViewModel = hiltViewModel(),
    onItemClick: (WrongAnswer) -> Unit = {},
    onRetryClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadWrongAnswers()
    }

    Scaffold(
        containerColor = BitQuestColors.White,
        topBar = {
            TopAppBar(
                title = { Text("오답 노트", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BitQuestColors.PrimaryGreen,
                    titleContentColor = Color.White
                )
            )
        }
    ) { inner ->
        WrongNoteContent(
            modifier = Modifier.padding(inner),
            uiState = uiState,
            onItemClick = onItemClick,
            onRetryClick = onRetryClick
        )
    }
}

@Composable
private fun WrongNoteContent(
    modifier: Modifier = Modifier,
    uiState: WrongAnswerUiState,
    onItemClick: (WrongAnswer) -> Unit,
    onRetryClick: () -> Unit
) {
    when {
        uiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = BitQuestColors.PrimaryGreen)
            }
        }

        uiState.error != null -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "오류가 발생했습니다: ${uiState.error}",
                    color = Color.Red
                )
            }
        }

        uiState.wrongAnswers.isEmpty() -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Outlined.LibraryBooks,
                        contentDescription = null,
                        tint = BitQuestColors.PrimaryGreen,
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(Modifier.height(12.dp))
                    Text("아직 저장된 오답이 없어요", style = MaterialTheme.typography.titleMedium, color = BitQuestColors.TextDark)
                    Spacer(Modifier.height(8.dp))
                    Text("퀴즈를 풀고 틀린 문제를 여기서 복습하세요", style = MaterialTheme.typography.bodyMedium, color = BitQuestColors.TextLight)
                    Spacer(Modifier.height(16.dp))
                    Button(
                        onClick = onRetryClick,
                        colors = ButtonDefaults.buttonColors(containerColor = BitQuestColors.PrimaryGreen)
                    ) {
                        Text("퀴즈 시작", color = Color.White)
                    }
                }
            }
        }

        else -> {
            LazyColumn(
                modifier = modifier.fillMaxSize()
            ) {
                items(uiState.wrongAnswers) { wrongAnswer ->
                    ListItem(
                        headlineContent = {
                            Text(
                                wrongAnswer.question.ifBlank { "(no text)" },
                                color = BitQuestColors.TextDark
                            )
                        },
                        supportingContent = {
                            Text(
                                "Your: ${wrongAnswer.userAnswer}  |  Correct: ${wrongAnswer.correctAnswer}",
                                color = BitQuestColors.TextLight
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onItemClick(wrongAnswer) }
                    )
                    HorizontalDivider(color = BitQuestColors.BackgroundGray)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WrongNoteScreenPreview_Empty() {
    MaterialTheme {
        WrongNoteContent(
            uiState = WrongAnswerUiState(),
            onItemClick = {},
            onRetryClick = {}
        )
    }
}