package com.largeblueberry.bitquest.feature_gemini.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.largeblueberry.bitquest.feature_gemini.AnalysisResult
import com.largeblueberry.bitquest.feature_gemini.AnalysisUiState
import com.largeblueberry.bitquest.feature_gemini.ui.AnalysisViewModel
import com.largeblueberry.bitquest.feature_gemini.WrongAnswer

// 컬러 팔레트 정의
object AnalysisColors {
    val Background = Color(0xFFFAFAFA)
    val Surface = Color.White
    val Primary = Color(0xFF1976D2)
    val Success = Color(0xFF4CAF50)
    val Warning = Color(0xFFFF9800)
    val Error = Color(0xFFF44336)
    val TextPrimary = Color(0xFF212121)
    val TextSecondary = Color(0xFF757575)
    val Divider = Color(0xFFE0E0E0)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalysisScreen(
    wrongAnswers: List<WrongAnswer>,
    onBackClick: () -> Unit = {},
    viewModel: AnalysisViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(wrongAnswers) {
        if (wrongAnswers.isNotEmpty()) {
            viewModel.analyzeWrongAnswers(wrongAnswers)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "📊 오답 분석 결과",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = AnalysisColors.TextPrimary
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "뒤로가기",
                            tint = AnalysisColors.TextPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AnalysisColors.Surface
                )
            )
        },
        containerColor = AnalysisColors.Background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            when (uiState) {
                is AnalysisUiState.Idle -> {
                    EmptyStateContent()
                }
                is AnalysisUiState.Loading -> {
                    LoadingContent()
                }
                is AnalysisUiState.Success -> {
                    AnalysisResultContent(result = (uiState as AnalysisUiState.Success).result)
                }
                is AnalysisUiState.Error -> {
                    ErrorContent(
                        message = (uiState as AnalysisUiState.Error).message,
                        onRetry = { viewModel.analyzeWrongAnswers(wrongAnswers) }
                    )
                }
            }
        }
    }
}

@Composable
private fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = AnalysisColors.Primary,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "🤖 AI가 오답을 분석중입니다...",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = AnalysisColors.TextPrimary
                )
            )
        }
    }
}

@Composable
private fun AnalysisResultContent(result: AnalysisResult) {
    LazyColumn(
        modifier = Modifier.padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 전체 점수
        item {
            ScoreCard(score = result.overallScore)
        }

        // 약한 분야
        item {
            WeakAreasCard(weakAreas = result.weakAreas)
        }

        // 추천사항
        item {
            RecommendationsCard(recommendations = result.recommendations)
        }

        // 학습 계획
        item {
            StudyPlanCard(studyPlan = result.studyPlan)
        }

        // 상세 피드백
        item {
            DetailedFeedbackCard(feedback = result.detailedFeedback)
        }
    }
}

@Composable
private fun ScoreCard(score: Int) {
    val (scoreColor, bgColor, emoji) = when {
        score >= 80 -> Triple(AnalysisColors.Success, AnalysisColors.Success.copy(alpha = 0.1f), "🎉")
        score >= 60 -> Triple(AnalysisColors.Warning, AnalysisColors.Warning.copy(alpha = 0.1f), "👍")
        else -> Triple(AnalysisColors.Error, AnalysisColors.Error.copy(alpha = 0.1f), "💪")
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = AnalysisColors.Surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(bgColor)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "$emoji 전체 점수",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = AnalysisColors.TextPrimary,
                        fontWeight = FontWeight.Medium
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "$score 점",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        color = scoreColor,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = when {
                        score >= 80 -> "훌륭해요!"
                        score >= 60 -> "좋아요!"
                        else -> "더 노력해요!"
                    },
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = AnalysisColors.TextSecondary
                    )
                )
            }
        }
    }
}

@Composable
private fun WeakAreasCard(weakAreas: List<String>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = AnalysisColors.Surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = "⚠️ 약한 분야",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = AnalysisColors.TextPrimary,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.padding(bottom = 12.dp)
            )
            weakAreas.forEach { area ->
                Row(
                    modifier = Modifier.padding(vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = null,
                        tint = AnalysisColors.Warning,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = area,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = AnalysisColors.TextPrimary
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun RecommendationsCard(recommendations: List<String>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = AnalysisColors.Surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = "✅ 추천사항",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = AnalysisColors.TextPrimary,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.padding(bottom = 12.dp)
            )
            recommendations.forEach { recommendation ->
                Row(
                    modifier = Modifier.padding(vertical = 6.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = AnalysisColors.Success,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = recommendation,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = AnalysisColors.TextPrimary,
                            lineHeight = 22.sp
                        ),
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun StudyPlanCard(studyPlan: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = AnalysisColors.Surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = "📚 학습 계획",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = AnalysisColors.TextPrimary,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.padding(bottom = 12.dp)
            )
            Text(
                text = studyPlan,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = AnalysisColors.TextPrimary,
                    lineHeight = 22.sp
                ),
            )
        }
    }
}

@Composable
private fun DetailedFeedbackCard(feedback: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = AnalysisColors.Surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = "💬 상세 피드백",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = AnalysisColors.TextPrimary,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.padding(bottom = 12.dp)
            )
            Text(
                text = feedback,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = AnalysisColors.TextPrimary,
                    lineHeight = 22.sp
                )
            )
        }
    }
}

@Composable
private fun ErrorContent(
    message: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Error,
            contentDescription = null,
            tint = AnalysisColors.Error,
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "😅 앗, 문제가 발생했어요!",
            style = MaterialTheme.typography.headlineSmall.copy(
                color = AnalysisColors.TextPrimary,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = message,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = AnalysisColors.TextSecondary
            ),
            modifier = Modifier.padding(horizontal = 32.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = onRetry,
            colors = ButtonDefaults.buttonColors(
                containerColor = AnalysisColors.Primary
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "🔄 다시 시도",
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun EmptyStateContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "📝",
            style = MaterialTheme.typography.displayMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "분석할 오답이 없어요",
            style = MaterialTheme.typography.headlineSmall.copy(
                color = AnalysisColors.TextPrimary,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "문제를 풀고 다시 돌아와주세요! 🚀",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = AnalysisColors.TextSecondary
            )
        )
    }
}