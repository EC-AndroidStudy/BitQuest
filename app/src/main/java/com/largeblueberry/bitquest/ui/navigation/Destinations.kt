package com.largeblueberry.bitquest.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

/**
 * [작업 담당자: Android 개발자 (주니어 가능)]
 *
 * 네비게이션 그래프에서 사용될 인자들의 키를 정의합니다.
 */
object NavArgumentKeys {
    const val QUIZ_ID = "quizId"
    const val RESULT_ID = "resultId" // 결과 화면을 위한 ID
}

/**
 * 앱 내의 모든 네비게이션 목적지를 타입 안전하게 정의하는 sealed class 입니다.
 * 각 목적지는 'screens' 패키지 구조를 기반으로 고유한 라우트를 가집니다.
 *
 * @param route 목적지의 경로 문자열
 */
sealed class Screen(val route: String) {
    // 메인 화면 (퀴즈 목록 포함)
    object Main : Screen("main_route")

    // 프로필 화면
    object Profile : Screen("profile_route")

    // 퀴즈 화면

    // 퀴즈 상세 화면 (동적인 quizId를 인자로 받음)
    object QuizDetail : Screen("quiz_detail_route/{${NavArgumentKeys.QUIZ_ID}}") {
        fun createRoute(quizId: String) = "quiz_detail_route/$quizId"
    }

    // 결과 화면 (동적인 resultId를 인자로 받음)
    object Result : Screen("result_route/{${NavArgumentKeys.RESULT_ID}}") {
        fun createRoute(resultId: String) = "result_route/$resultId"
    }
}

/**
 * Bottom Navigation에 표시될 화면들의 목록입니다.
 * MainScreen과 ProfileScreen을 주요 탭으로 설정합니다.
 */
val bottomNavItems = listOf(
    Screen.Main,
    Screen.Profile
)