package com.largeblueberry.bitquest.feature_main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.largeblueberry.bitquest.feature_main.util.BitQuestColors
import com.largeblueberry.bitquest.feature_main.util.HeaderItem
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.largeblueberry.bitquest.R
import com.largeblueberry.bitquest.ui.navigation.NavArgumentKeys.QUIZ_ID
import com.largeblueberry.bitquest.ui.navigation.Screen

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        containerColor = BitQuestColors.White
    ) { innerPadding ->
        BitQuestContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding), // 시스템 UI 영역만 피하기
            cardData = uiState.cardData,
            onSolveClick = {
                // 테마 선택 화면으로 이동하도록 수정
                navController.navigate(Screen.FieldSelection.route)
            },
            onAiAnalysisClick = { viewModel.onAiAnalysis() },
            onMainClick = { viewModel.onNavigateToMain() },
            onMyPageClick = { viewModel.onNavigateToMyPage() }
        )
    }

}

@Composable
fun BitQuestContent(
    modifier: Modifier = Modifier,
    cardData: BitQuestCardData,
    onSolveClick: () -> Unit = {},
    onAiAnalysisClick: () -> Unit = {},
    onMainClick: () -> Unit = {},
    onMyPageClick: () -> Unit = {}
) {
    Column(modifier = modifier.fillMaxSize()) {
        // 상단 헤더
        HeaderSection(
            tier = cardData.tier,
            description = cardData.description,
            heartCount = cardData.heartCount
        )

        // 메인 콘텐츠
        MainContentSection(
            modifier = Modifier.weight(1f),
            title = cardData.title,
            score = cardData.score,
            accuracy = cardData.accuracy,
            onSolveClick = onSolveClick
        )

        // 하단 네비게이션
        NavigationSection(
            onAiAnalysisClick = onAiAnalysisClick,
            onMainClick = onMainClick,
            onMyPageClick = onMyPageClick
        )
    }
}

@Composable
private fun HeaderSection(
    tier: String,
    description: String,
    heartCount: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(BitQuestColors.White)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        HeaderItem(text = tier, weight = 1f)
        HeaderItem(text = description, weight = 2f)
        // 하트 이미지와 개수
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.heartbutton), // hearbutton.png
                contentDescription = "하트",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "5", // 하드코딩된 하트 개수 (나중에 heartCount로 변경)
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = BitQuestColors.TextDark
            )
        }
    }
}

@Composable
private fun MainContentSection(
    modifier: Modifier = Modifier,
    title: String,
    score: String,
    accuracy: String,
    onSolveClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(BitQuestColors.BackgroundGray)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 타이틀
        Text(
            text = title,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = BitQuestColors.TextDark,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 점수와 정답률
        Row(
            horizontalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "점수",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = BitQuestColors.TextDark
                )
                Text(
                    text = score,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = BitQuestColors.TextDark
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "정확도",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = BitQuestColors.TextDark
                )
                Text(
                    text = accuracy,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = BitQuestColors.TextDark
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 문제 풀러 가기 버튼
        Button(
            onClick = onSolveClick,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BitQuestColors.PrimaryGreen
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "문제 풀러 가기",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }
    }
}

@Composable
private fun NavigationSection(
    onAiAnalysisClick: () -> Unit,
    onMainClick: () -> Unit,
    onMyPageClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(BitQuestColors.White) // 흰색 배경으로 변경
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.aibutton), // aibutton.png
            contentDescription = "AI 오답 분석",
            modifier = Modifier
                .size(48.dp)
                .clickable { onAiAnalysisClick() }
        )

        // 홈 버튼 (메인)
        Image(
            painter = painterResource(id = R.drawable.homebutton), // homebutton.png
            contentDescription = "메인",
            modifier = Modifier
                .size(48.dp)
                .clickable { onMainClick() }
        )// -> 일일 과제 화면으로 개선

        Image(
            painter = painterResource(id = R.drawable.settingbutton), // mypagebutton.png
            contentDescription = "마이페이지",
            modifier = Modifier
                .size(48.dp)
                .clickable { onMyPageClick() }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MaterialTheme {
        MainScreen(navController = rememberNavController())
    }
}