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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.model.WrongAnswerNote
import com.largeblueberry.bitquest.feature_main.util.BitQuestColors
import com.largeblueberry.bitquest.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WrongNoteScreen(
    navController: NavController,
    viewModel: WrongNoteViewModel = hiltViewModel(),
    onItemClick: (WrongAnswerNote) -> Unit = {}
) {
    val notes by viewModel.notes.collectAsState()

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
            notes = notes,
            onItemClick = onItemClick,
            onRetryClick = { navController.navigate(Screen.FieldSelection.route) }
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
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(notes) { note ->
                WrongNoteItem(note = note, onClick = { onItemClick(note) })
                Divider(color = BitQuestColors.BackgroundGray, thickness = 1.dp)
            }
        }
    }
}

@Composable
private fun WrongNoteItem(note: WrongAnswerNote, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // 제목 (12자 이상이면 ... 처리)
        Text(
            text = note.title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = BitQuestColors.TextDark,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f, fill = false)
        )

        // 카테고리 아이콘 및 텍스트
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = when (note.category) {
                    "Android" -> "📱"
                    "Git" -> "🌿"
                    else -> "📖"
                },
                fontSize = 21.sp, // 1pt 키움
                modifier = Modifier.padding(start = 16.dp, end = 8.dp)
            )
            Text(
                text = note.category,
                fontSize = 17.sp, // 1pt 키움
                fontWeight = FontWeight.Medium,
                color = BitQuestColors.TextLight
            )
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