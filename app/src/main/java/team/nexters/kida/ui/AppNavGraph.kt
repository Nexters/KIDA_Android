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
import team.nexters.kida.ui.keyword.KeywordConfirmScreen
import team.nexters.kida.ui.keyword.KeywordSelectScreen
import team.nexters.kida.ui.list.ListScreen
import team.nexters.kida.ui.splash.SplashScreen
import team.nexters.kida.ui.write.WriteScreen

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Keyword : Screen("keyword")
    object KeywordConfirm : Screen("keyword-confirm")
    object List : Screen("list")
    object Write : Screen("write")
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
        addWrite(navController)
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
        KeywordSelectScreen(
            onNavigate = { keyword ->
                navController.navigate(
                    "${Screen.KeywordConfirm.route}?keyword=${keyword.name}",
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

    composable(
        route = "${Screen.KeywordConfirm.route}?keyword={keyword}",
        arguments = listOf(
            navArgument("keyword") { type = NavType.StringType }
        )
    ) {
        val arguments = requireNotNull(it.arguments)
        val keyword = arguments.getString("keyword")
        KeywordConfirmScreen(
            keyword = keyword ?: "",
            upPress = { navController.popBackStack() },
            onConfirm = { newKeyword ->
                navController.navigate(
                    Screen.Write.route + "?diaryId={diaryId}&keyword=$newKeyword",
                    navOptions {
                        popUpTo(Screen.KeywordConfirm.route) {
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

private fun NavGraphBuilder.addWrite(
    navController: NavController
) {
    composable(
        route = Screen.Write.route + "?diaryId={diaryId}&keyword={keyword}",
        arguments = listOf(
            diaryIdArgument(),
            navArgument("keyword") { type = NavType.StringType }
        )
    ) {
        BackHandler(onBack = { navController.popBackStack() })
        WriteScreen(
            onPopBackStack = {
                navController.popBackStack()
            },
            keyword = it.arguments?.getString("keyword") ?: ""
        )
    }
}

private fun diaryIdArgument() = navArgument(name = "diaryId") {
    type = NavType.IntType
    defaultValue = -1
}
