package com.largeblueberry.bitquest.ui.navigation

// sealed class로 정의하여 route들을 그룹화합니다.
sealed class Screen(val route: String) {
    object Main : Screen("main")
    // 특정 퀴즈 상세
    object QuizDetail : Screen("quiz_detail/{${NavArgumentKeys.QUIZ_ID}}") {
        fun createRoute(quizId: Int) = "quiz_detail/$quizId"
    }

    // 🆕 카테고리별 퀴즈 목록 (새로운 Screen)
    object QuizCategory : Screen("quiz_category/{category}") {
        fun createRoute(category: String) = "quiz_category/$category"
    }
    object FieldSelection : Screen("field_selection")
}