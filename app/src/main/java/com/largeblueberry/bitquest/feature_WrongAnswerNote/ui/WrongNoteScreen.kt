package com.largeblueberry.bitquest.feature_WrongAnswerNote.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LibraryBooks
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.model.WrongAnswerNote
import com.largeblueberry.bitquest.feature_main.util.BitQuestColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WrongNoteScreen(
    viewModel: WrongNoteViewModel = hiltViewModel(),
    onItemClick: (WrongAnswerNote) -> Unit = {},
    onRetryClick: () -> Unit = {}
) {
    val notes by viewModel.notes.collectAsState()

    Scaffold(
        containerColor = BitQuestColors.White, // 전체 배경을 흰색으로 설정
        topBar = {
            TopAppBar(
                title = { Text("오답 노트", color = Color.White) }, // 타이틀 색상을 흰색으로
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BitQuestColors.PrimaryGreen, // 상단바 배경을 메인 초록색으로
                    titleContentColor = Color.White
                )
            )
        }
    ) { inner ->
        WrongNoteContent(
            modifier = Modifier.padding(inner),
            notes = notes,
            onItemClick = onItemClick,
            onRetryClick = onRetryClick
        )
    }
}

@Composable
private fun WrongNoteContent(
    modifier: Modifier = Modifier,
    notes: List<WrongAnswerNote>,
    onItemClick: (WrongAnswerNote) -> Unit,
    onRetryClick: () -> Unit
) {
    if (notes.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Outlined.LibraryBooks,
                    contentDescription = null,
                    tint = BitQuestColors.PrimaryGreen, // 아이콘 색상을 메인 초록색으로
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
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            items(notes) { n ->
                ListItem(
                    headlineContent = { Text(n.questionText.ifBlank { "(no text)" }, color = BitQuestColors.TextDark) }, // 폰트 색상을 검은색으로
                    supportingContent = {
                        Text("Your: ${n.selectedAnswer}  |  Correct: ${n.correctAnswer}", color = BitQuestColors.TextLight) // 폰트 색상을 회색으로
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onItemClick(n) }
                )
                Divider(color = BitQuestColors.BackgroundGray) // 구분선 색상 변경
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WrongNoteScreenPreview_Empty() {
    MaterialTheme {
        WrongNoteContent(notes = emptyList(), onItemClick = {}, onRetryClick = {})
    }
}
