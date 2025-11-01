package com.largeblueberry.bitquest.feature_quiz.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExplanationBlock(text: String) {
    TextBlock(title = "해설", content = text, backgroundColor = Color(0xFFE0F7FA))
}

@Composable
fun ResultBlock(isCorrect: Boolean, message: String) {
    val backgroundColor = if (isCorrect) Color(0xFFE8F5E8) else Color(0xFFFFEBEE)
    TextBlock(title = "결과", content = message, backgroundColor = backgroundColor)
}

@Composable
private fun TextBlock(title: String, content: String, backgroundColor: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = content, fontSize = 16.sp)
        }
    }
}
