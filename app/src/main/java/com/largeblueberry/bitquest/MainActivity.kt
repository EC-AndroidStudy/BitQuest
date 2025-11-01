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
import com.largeblueberry.bitquest.ui.navigation.Screen
import com.largeblueberry.bitquest.ui.theme.BitQuestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BitQuestTheme {
                // 앱의 전체 네비게이션을 관리할 NavHost 설정
                BitQuestApp()
            }
        }
    }
}

@Composable
fun BitQuestApp() {
    // 네비게이션 컨트롤러 생성 및 기억
    val navController: NavHostController = rememberNavController()

    // NavHost: 네비게이션 그래프를 호스팅하는 컨테이너
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route // 앱 시작 화면
    ) {
        // MainScreen 라우팅 설정
        composable(route = Screen.Main.route) {
            MainScreen(
                // 클릭 이벤트가 발생하면, 선택된 테마 이름으로 QuizList 화면으로 이동
                onThemeClick = { theme ->
                    //그 테마의 퀴즈 화면 보여줌.
                }
            )
        }

        // TODO: 여기에 다른 화면들(QuizListScreen, QuizDetailScreen 등)의
        // composable() 정의를 추가해야 합니다.
        // 예시:
        // composable(
        //     route = Screen.QuizList.route,
        //     arguments = Screen.QuizList.navArguments
        // ) { backStackEntry ->
        //     val themeName = backStackEntry.arguments?.getString(NavArgumentKeys.THEME_NAME)
        //     if (themeName != null) {
        //         QuizListScreen(
        //             themeName = themeName,
        //             onQuizClick = { quizId -> /* 퀴즈 상세 화면으로 이동 */ },
        //             onBackClick = { navController.popBackStack() }
        //         )
        //     }
        // }
    }
}