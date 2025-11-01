package com.largeblueberry.bitquest.feature_ThemeSelection

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.largeblueberry.bitquest.feature_main.util.BitQuestColors
import com.largeblueberry.bitquest.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeSelectionScreen(
    navController: NavController
) {
    val themes = remember {
        listOf(
            ThemeItem(
                id = 1,
                title = "기초 프로그래밍",
                description = "변수, 조건문, 반복문",
                backgroundColor = BitQuestColors.PrimaryGreen
            ),
            ThemeItem(
                id = 2,
                title = "자료구조",
                description = "배열, 리스트, 스택, 큐",
                backgroundColor = Color(0xFF4CAF50)
            ),
            ThemeItem(
                id = 3,
                title = "알고리즘",
                description = "정렬, 탐색, 그래프",
                backgroundColor = Color(0xFF2196F3)
            ),
            ThemeItem(
                id = 4,
                title = "객체지향",
                description = "클래스, 상속, 다형성",
                backgroundColor = Color(0xFF9C27B0)
            ),
            ThemeItem(
                id = 5,
                title = "데이터베이스",
                description = "SQL, 관계형 DB",
                backgroundColor = Color(0xFFFF9800),
                isLocked = true
            ),
            ThemeItem(
                id = 6,
                title = "네트워크",
                description = "HTTP, API, 통신",
                backgroundColor = Color(0xFFF44336),
                isLocked = true
            )
        )
    }

    Scaffold(
        containerColor = BitQuestColors.White,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "테마 선택",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = BitQuestColors.TextDark
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_menu_revert),
                            contentDescription = "뒤로가기",
                            tint = BitQuestColors.TextDark
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BitQuestColors.White
                )
            )
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(themes) { theme ->
                ThemeCard(
                    theme = theme,
                    onClick = {
                        if (!theme.isLocked) {
                            // 퀴즈 상세 화면으로 이동 (테마 ID를 퀴즈 ID로 사용)
                            navController.navigate(Screen.QuizDetail.createRoute(theme.id))
                        }
                    }
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun ThemeSelectionScreenPreview() {
    MaterialTheme {
        ThemeSelectionScreen(navController = rememberNavController())
    }
}