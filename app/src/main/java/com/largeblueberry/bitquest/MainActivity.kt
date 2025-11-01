package com.largeblueberry.bitquest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.largeblueberry.bitquest.feature_main.MainScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.largeblueberry.bitquest.feature_quiz.ui.QuizDetailScreen
import com.largeblueberry.bitquest.ui.navigation.AppNavGraph
import com.largeblueberry.bitquest.ui.navigation.Screen
import com.largeblueberry.bitquest.ui.theme.BitQuestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BitQuestTheme {

                // 1. NavController를 앱의 최상위 수준에서 생성합니다.
                val navController = rememberNavController()

                // 2. MainScreen 대신 AppNavGraph를 진입점으로 설정합니다.
                // 이제 AppNavGraph가 화면 전환을 모두 관리합니다.
                AppNavGraph(navController = navController)

            }
        }
    }
}