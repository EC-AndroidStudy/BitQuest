package com.largeblueberry.bitquest.ui.navigation

// sealed class로 정의하여 route들을 그룹화합니다.
sealed class Screen(val route: String) {
    object Main : Screen("main")
    object QuizDetail : Screen("quiz_detail/{${NavArgumentKeys.QUIZ_ID}}") {
        // 퀴즈 ID를 받아 완전한 route 경로를 생성하는 함수
        fun createRoute(quizId: Int) = "quiz_detail/$quizId"
    }
    object FieldSelection : Screen("field_selection")
}