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
import com.largeblueberry.bitquest.feature_ThemeSelection.ThemeSelectionScreen
import com.largeblueberry.bitquest.feature_main.MainScreen
import com.largeblueberry.bitquest.feature_quiz.ui.QuizDetailScreen


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

        // 퀴즈 상세 화면
        composable(
            route = Screen.QuizDetail.route,
            arguments = listOf(navArgument(NavArgumentKeys.QUIZ_ID) { type = NavType.IntType }),
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(350)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(350)) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(350)) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(350)) }
        ) { backStackEntry ->
            val quizId = backStackEntry.arguments?.getInt(NavArgumentKeys.QUIZ_ID) ?: ""
            QuizDetailScreen(
                navController = navController,
            )
        }

        composable(Screen.ThemeSelection.route) {
            ThemeSelectionScreen(navController = navController)
        }
    }
}