package com.largeblueberry.bitquest.feature_main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.DataObject
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.largeblueberry.bitquest.ui.theme.BitQuestTheme


/**
 * 퀴즈 주제를 나타내는 데이터 클래스
 * @param name 주제 이름
 * @param icon 주제를 나타내는 아이콘
 */
data class QuizTheme(val name: String, val icon: ImageVector)

/**
 * 메인 화면. 여러 퀴즈 주제를 버튼 형태로 보여줍니다.
 *
 * @param onThemeClick 퀴즈 주제 버튼이 클릭되었을 때 호출되는 콜백
 * @param modifier UI 수정을 위한 Modifier
 */
@Composable
fun MainScreen(
    onThemeClick: (QuizTheme) -> Unit,
    modifier: Modifier = Modifier
) {
    // 샘플 데이터
    val themes = listOf(
        QuizTheme("Kotlin", Icons.Filled.Code),
        QuizTheme("Data Structure", Icons.Filled.DataObject),
        QuizTheme("Algorithm", Icons.Filled.Computer),
        QuizTheme("Database", Icons.Filled.Storage),
        QuizTheme("Network", Icons.Filled.Cloud),
        QuizTheme("OS", Icons.Filled.Android)
    )

    Scaffold(
        topBar = { TopBar() },
        modifier = modifier
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.padding(paddingValues)
        ) {
            items(themes) { theme ->
                ThemeButton(
                    theme = theme,
                    onClick = { onThemeClick(theme) }
                )
            }
        }
    }
}

/**
 * 화면 상단에 표시될 TopAppBar
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = { Text("BitQuest") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        )
    )
}

/**
 * 재사용 가능한 퀴즈 주제 버튼
 * @param theme 표시할 퀴즈 주제
 * @param onClick 버튼 클릭 시 호출될 람다
 * @param modifier UI 수정을 위한 Modifier
 */
@Composable
fun ThemeButton(
    theme: QuizTheme,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .aspectRatio(1f),
        onClick = onClick,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = theme.icon,
                contentDescription = null, // 아이콘은 장식용이므로 null 처리
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = theme.name,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    BitQuestTheme {
        MainScreen(onThemeClick = {})
    }
}

@Preview(showBackground = true, widthDp = 200)
@Composable
private fun ThemeButtonPreview() {
    BitQuestTheme {
        ThemeButton(
            theme = QuizTheme("Kotlin", Icons.Filled.Code),
            onClick = {}
        )
    }
}
