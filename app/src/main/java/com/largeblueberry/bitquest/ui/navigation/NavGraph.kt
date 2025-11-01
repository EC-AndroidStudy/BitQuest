package com.largeblueberry.bitquest.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.largeblueberry.bitquest.feature_FieldSelection.FieldSelectionScreen
import com.largeblueberry.bitquest.feature_main.MainScreen
import com.largeblueberry.bitquest.feature_quiz.ui.QuizDetailScreen
import com.largeblueberry.bitquest.feature_WrongAnswerNote.ui.WrongNoteScreen


@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = Screen.Main.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        // 메인 화면
        composable(
            route = Screen.Main.route,
            enterTransition = { fadeIn(animationSpec = tween(350)) },
            exitTransition = { fadeOut(animationSpec = tween(350)) }
        ) {
            MainScreen(navController = navController)
        }

        // 퀴즈 상세/목록 화면 (카테고리 기반으로 통합)
        // 라우트: quiz_detail?category={category}
        composable(
            route = "${Screen.QuizDetail.route}?category={category}",
            arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                    nullable = true // 카테고리가 없을 수도 있으므로 nullable 설정
                }
            ),
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(350)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(350)) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(350)) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(350)) }
        ) { backStackEntry ->
            // category를 안전하게 추출
            val category = backStackEntry.arguments?.getString("category")

            // QuizDetailScreen에서 category를 사용하여 퀴즈 목록을 로드합니다.
            QuizDetailScreen(
                navController = navController,
                category = category // 카테고리 전달
            )
        }

        composable(Screen.FieldSelection.route) {
            FieldSelectionScreen(navController = navController)
        }

        composable(Screen.ReviewNotes.route) {
            WrongNoteScreen()
        }
    }
}