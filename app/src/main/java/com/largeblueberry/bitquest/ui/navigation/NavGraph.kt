package com.largeblueberry.bitquest.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
// import com.largeblueberry.bitquest.ui.screens.MainScreen
// import com.largeblueberry.bitquest.ui.screens.ProfileScreen
// import com.largeblueberry.bitquest.ui.screens.QuizDetailScreen
// import com.largeblueberry.bitquest.ui.screens.ResultScreen


/**
 * [작업 담당자: Android 개발자 (Navigation 경험자)]
 *
 * 앱의 전체 네비게이션 흐름을 정의하는 NavHost 입니다.
 */
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
            // MainScreen(navController = navController)
        }

        // 프로필 화면
        composable(
            route = Screen.Profile.route,
            enterTransition = { fadeIn(animationSpec = tween(350)) },
            exitTransition = { fadeOut(animationSpec = tween(350)) }
        ) {
            // ProfileScreen(navController = navController)
        }

        // 퀴즈 상세 화면
        composable(
            route = Screen.QuizDetail.route,
            arguments = listOf(navArgument(NavArgumentKeys.QUIZ_ID) { type = NavType.StringType }),
            deepLinks = listOf(navDeepLink { uriPattern = "https://bitquest.app/quiz/{${NavArgumentKeys.QUIZ_ID}}" }),
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(350)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(350)) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(350)) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(350)) }
        ) { backStackEntry ->
            val quizId = backStackEntry.arguments?.getString(NavArgumentKeys.QUIZ_ID)
            // QuizDetailScreen(quizId = quizId, navController = navController)
        }

        // 결과 화면
        composable(
            route = Screen.Result.route,
            arguments = listOf(navArgument(NavArgumentKeys.RESULT_ID) { type = NavType.StringType }),
            enterTransition = { fadeIn(animationSpec = tween(350)) },
            exitTransition = { fadeOut(animationSpec = tween(350)) }
        ) { backStackEntry ->
            val resultId = backStackEntry.arguments?.getString(NavArgumentKeys.RESULT_ID)
            // ResultScreen(resultId = resultId, navController = navController)
        }
    }
}

/**
 * Bottom Navigation 사용 시, 동일한 메뉴를 다시 클릭했을 때 백스택이 중복으로 쌓이는 것을 방지하고
 * 상태를 유지하기 위한 네비게이션 확장 함수입니다.
 */
fun NavHostController.navigateWithStateSaving(route: String) {
    this.navigate(route) {
        popUpTo(this@navigateWithStateSaving.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}