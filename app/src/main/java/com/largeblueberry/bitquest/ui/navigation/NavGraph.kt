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
        composable(
            route = "${Screen.QuizDetail.route}?category={category}",
            arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                    nullable = true
                }
            ),
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(350)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(350)) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(350)) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(350)) }
        ) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category")

            QuizDetailScreen(
                navController = navController,
                category = category
            )
        }

        composable(Screen.FieldSelection.route) {
            FieldSelectionScreen(navController = navController)
        }

        composable(Screen.ReviewNotes.route) {
            WrongNoteScreen(
                navController = navController,
                onItemClick = { note ->
                    // 클릭된 아이템의 questionId를 사용하여 퀴즈 상세 화면으로 이동
                    navController.navigate(Screen.QuizDetail.createRoute(note.questionId))
                }
            )
        }
    }
}