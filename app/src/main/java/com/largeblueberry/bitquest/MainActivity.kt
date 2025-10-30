package com.largeblueberry.bitquest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.largeblueberry.bitquest.ui.screens.QuizDetailScreen
import com.largeblueberry.bitquest.ui.theme.BitQuestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // BitQuestTheme는 앱의 전체적인 디자인(색상, 글꼴 등)을 정의합니다.
            // 이 안에 화면을 넣으면 일관된 디자인을 유지할 수 있습니다.
            BitQuestTheme {
                QuizDetailScreen()
            }
        }
    }
}