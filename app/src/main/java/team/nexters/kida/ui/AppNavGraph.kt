package team.nexters.kida.ui

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import team.nexters.kida.ui.detail.DetailScreen
import team.nexters.kida.ui.keyword.KeywordScreen
import team.nexters.kida.ui.list.ListScreen
import team.nexters.kida.ui.splash.SplashScreen
import team.nexters.kida.ui.write.WriteScreen

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Keyword : Screen("keyword")
    object List : Screen("list")
    object Write : Screen("write")
    object Detail : Screen("detail")
}

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        addSplash(navController)
        addKeyword(navController)
        addList(navController)
        composable(
            route = Screen.Write.route + "?diaryId={diaryId}",
            arguments = listOf(diaryIdArgument())
        ) {
            BackHandler(onBack = { navController.popBackStack() })
            WriteScreen(
                onPopBackStack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = Screen.Detail.route + "?diaryId={diaryId}",
            arguments = listOf(diaryIdArgument())
        ) {
            BackHandler(onBack = { navController.popBackStack() })
            DetailScreen(
                onNavigate = {
                    navController.navigate(it.route)
                },
                onPopBackStack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

private fun NavGraphBuilder.addSplash(
    navController: NavController
) {
    composable(Screen.Splash.route) {
        SplashScreen(
            onNavigate = {
                navController.navigate(
                    it.route,
                    navOptions {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                )
            }
        )
    }
}

private fun NavGraphBuilder.addKeyword(
    navController: NavController
) {
    composable(Screen.Keyword.route) { _: NavBackStackEntry ->
        KeywordScreen(
            onNavigate = {
                navController.navigate(
                    it.route,
                    navOptions {
                        popUpTo(Screen.Keyword.route) {
                            saveState = true
                        }
                        restoreState = true
                    }
                )
            }
        )
    }
}

private fun NavGraphBuilder.addList(
    navController: NavController
) {
    composable(Screen.List.route) {
        ListScreen(
            onNavigate = {
                navController.navigate(it.route)
            }
        )
    }
}

private fun diaryIdArgument() = navArgument(name = "diaryId") {
    type = NavType.IntType
    defaultValue = -1
}
