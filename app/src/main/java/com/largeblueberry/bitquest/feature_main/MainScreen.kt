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
                .padding(innerPadding),
            cardData = uiState.cardData,
            onSolveClick = {
                navController.navigate(Screen.FieldSelection.route)
            },
            onWrongNoteClick = { navController.navigate(Screen.ReviewNotes.route) }, // 오답노트 화면으로 이동
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
    onWrongNoteClick: () -> Unit = {},
    onAiAnalysisClick: () -> Unit = {},
    onMainClick: () -> Unit = {},
    onMyPageClick: () -> Unit = {}
) {
    Column(modifier = modifier.fillMaxSize()) {
        HeaderSection(
            tier = cardData.tier,
            description = cardData.description,
            heartCount = cardData.heartCount
        )

        MainContentSection(
            modifier = Modifier.weight(1f),
            title = cardData.title,
            score = cardData.score,
            accuracy = cardData.accuracy,
            onSolveClick = onSolveClick,
            onWrongNoteClick = onWrongNoteClick // 추가
        )

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
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.heartbutton),
                contentDescription = "하트",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "5",
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
    onSolveClick: () -> Unit,
    onWrongNoteClick: () -> Unit // 추가
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(BitQuestColors.BackgroundGray)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = BitQuestColors.TextDark,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

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

        Button(
            onClick = onSolveClick,
            modifier = Modifier
                .fillMaxWidth()
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

        Spacer(modifier = Modifier.height(12.dp))

        // 오답노트 보기 버튼 추가
        Button(
            onClick = onWrongNoteClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BitQuestColors.WrongAnswerRed // 보색 계열
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "오답노트 보기",
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
            .background(BitQuestColors.White)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.aibutton),
            contentDescription = "AI 오답 분석",
            modifier = Modifier
                .size(48.dp)
                .clickable { onAiAnalysisClick() }
        )

        Image(
            painter = painterResource(id = R.drawable.homebutton),
            contentDescription = "메인",
            modifier = Modifier
                .size(48.dp)
                .clickable { onMainClick() }
        )

        Image(
            painter = painterResource(id = R.drawable.settingbutton),
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
