package com.largeblueberry.bitquest.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.largeblueberry.bitquest.domain.model.Choice
import com.largeblueberry.bitquest.domain.model.Quiz
import com.largeblueberry.bitquest.domain.model.QuizType

@Composable
fun QuizDetailScreen(
    viewModel: QuizDetailViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val quiz = uiState.quiz

    if (quiz == null) {
        // 퀴즈 데이터가 로딩 중일 때 보여줄 화면
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "퀴즈를 불러오는 중...")
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // 문제 블럭 (제목과 내용 분리)
            ProblemBlock(quiz = quiz)

            Spacer(modifier = Modifier.height(24.dp))

            // 해설 블럭 (답안 제출 시에만 표시)
            if (uiState.isAnswerSubmitted) {
                ExplanationBlock(text = quiz.explanation)
            }

            Spacer(modifier = Modifier.weight(1f)) // 선택지를 하단에 위치시키기 위한 빈 공간

            // 선택지 블럭 (퀴즈 유형에 따라 동적으로 변경)
            ChoicesBlock(quiz = quiz)
        }
    }
}

@Composable
fun ProblemBlock(quiz: Quiz) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // 퀴즈 제목
            Text(text = quiz.title, fontWeight = FontWeight.Bold, fontSize = 22.sp)
            Spacer(modifier = Modifier.height(16.dp))
            // 퀴즈 내용
            Text(text = quiz.content, fontSize = 16.sp)
        }
    }
}

@Composable
fun ExplanationBlock(text: String) {
    TextBlock(title = "해설", content = text, backgroundColor = Color(0xFFE0F7FA))
}

@Composable
fun TextBlock(title: String, content: String, backgroundColor: Color) {
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

@Composable
fun ChoicesBlock(quiz: Quiz) {
    when (quiz.type) {
        QuizType.MULTIPLE_CHOICE -> {
            MultipleChoiceButtons(choices = quiz.choices)
        }
        QuizType.OX -> {
            OxButtons()
        }
    }
}

@Composable
fun MultipleChoiceButtons(choices: List<Choice>) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        choices.forEach {
            Button(
                onClick = { /* TODO: 객관식 선택 이벤트 */ },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = it.text, modifier = Modifier.padding(8.dp))
            }
        }
    }
}

@Composable
fun OxButtons() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            onClick = { /* TODO: O 버튼 클릭 이벤트 */ },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
        ) {
            Text(text = "O", fontSize = 48.sp)
        }

        Button(
            onClick = { /* TODO: X 버튼 클릭 이벤트 */ },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
        ) {
            Text(text = "X", fontSize = 48.sp)
        }
    }
}