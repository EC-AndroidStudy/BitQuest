package com.largeblueberry.bitquest.feature_ThemeSelection

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ThemeCard(
    theme: ThemeItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp) // aspectRatio 대신 고정 높이 사용
            .clickable(enabled = !theme.isLocked) { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (theme.isLocked) Color.Gray.copy(alpha = 0.3f) else theme.backgroundColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp)
            ) {
                // 아이콘 대신 이모지 사용
                Text(
                    text = when(theme.id) {
                        1 -> "💻"
                        2 -> "📚"
                        3 -> "🧮"
                        4 -> "🏗️"
                        5 -> "🗃️"
                        6 -> "🌐"
                        else -> "📖"
                    },
                    fontSize = 32.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                // 제목
                Text(
                    text = theme.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (theme.isLocked) Color.Gray else Color.White,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(4.dp))

                // 설명
                Text(
                    text = theme.description,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (theme.isLocked) Color.Gray else Color.White.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center
                )
            }

            // 잠금 아이콘
            if (theme.isLocked) {
                Text(
                    text = "🔒",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                )
            }
        }
    }
}