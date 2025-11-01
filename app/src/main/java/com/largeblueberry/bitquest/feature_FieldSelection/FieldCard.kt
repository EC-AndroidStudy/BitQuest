package com.largeblueberry.bitquest.feature_FieldSelection

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
import com.largeblueberry.bitquest.feature_FieldSelection.domain.Field
import androidx.core.graphics.toColorInt

@Composable
fun FieldCard(
    field: Field,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .clickable { onClick() }, // enabled 조건 제거
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = field.colorHex.toColor()// 조건문 제거
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
                    text = when(field.id) {
                        1 -> "💻"
                        2 -> "📱"
                        3 -> "🌿"
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
                    text = field.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White, // 조건문 제거
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(4.dp))

                // 설명
                Text(
                    text = field.description,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White.copy(alpha = 0.8f), // 조건문 제거
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}

// Color 변환 확장 함수
private fun String.toColor(): Color {
    return try {
        Color(this.toColorInt())
    } catch (e: Exception) {
        Color.Gray // 기본값
    }
}