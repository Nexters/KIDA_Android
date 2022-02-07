package team.nexters.kida.ui

import android.os.Bundle
import android.os.Parcelable
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
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import team.nexters.kida.data.keyword.Keyword
import team.nexters.kida.ui.keyword.KeywordCard
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
    modifier: Modifier = Modifier,
    finishApp: () -> Unit
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        addSplash(navController)
        addKeyword(navController)
        addList(navController, finishApp)
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
    composable(Screen.Keyword.route) {
        KeywordSelectScreen(
            onNavigate = { keyword, card ->
                navController.navigate(
                    "${Screen.KeywordConfirm.route}?keyword=${keyword.encodedData()}&card=${card.encodedData()}",
                    navOptions {
                        popUpTo(Screen.Keyword.route) {
                            saveState = true
                        }
                        restoreState = true
                    }
                )
            },
            onInfoClick = {}
        )
    }

    composable(
        route = "${Screen.KeywordConfirm.route}?keyword={keyword}&card={card}",
        arguments = listOf(
            navArgument("keyword") {
                type = createParcelableNavType<Keyword>()
            },
            navArgument("card") {
                type = createParcelableNavType<KeywordCard>()
            }
        )
    ) {
        val arguments = requireNotNull(it.arguments)
        val keyword = requireNotNull(arguments.getParcelable<Keyword>("keyword"))
        val card = requireNotNull(arguments.getParcelable<KeywordCard>("card"))
        KeywordConfirmScreen(
            keyword = keyword,
            card = card,
            upPress = { navController.popBackStack() },
            onConfirm = { newKeyword ->
                navController.navigate(
                    Screen.Write.route + "?diaryId={diaryId}&keyword=${newKeyword.encodedData()}",
                    navOptions {
                        popUpTo(Screen.KeywordConfirm.route) {
                            saveState = true
                        }
                        restoreState = true
                    }
                )
            },
            onInfoClick = {
            }
        )
    }
}

private fun NavGraphBuilder.addList(
    navController: NavController,
    onBack: () -> Unit
) {
    composable(Screen.List.route) {
        BackHandler(onBack = onBack)
        ListScreen(
            onNavigate = {
                navController.navigate(it.route)
            }
        )
        // TODO clear back stacks
    }
}

private fun NavGraphBuilder.addWrite(
    navController: NavController
) {
    composable(
        route = Screen.Write.route + "?diaryId={diaryId}&keyword={keyword}",
        arguments = listOf(
            diaryIdArgument(),
            navArgument("keyword") { type = createParcelableNavType<Keyword>() }
        )
    ) {
        val keyword = requireNotNull(it.arguments?.getParcelable<Keyword>("keyword"))
        BackHandler(onBack = { navController.popBackStack() })
        WriteScreen(
            onPopBackStack = {
                navController.popBackStack()
            },
            onNavigateToList = {
                navController.navigate(Screen.List.route) {
                    launchSingleTop = true
                    popUpTo(Screen.Write.route + "?diaryId={diaryId}&keyword={keyword}") {
                        inclusive = true
                    }
                }
            },
            keyword = keyword
        )
    }
}

private fun diaryIdArgument() = navArgument(name = "diaryId") {
    type = NavType.IntType
    defaultValue = -1
}

// nav graph 에서 data 변환용
private inline fun <reified T : Parcelable> T.encodedData(): String {
    return Json.encodeToString(this)
}

inline fun <reified T : Parcelable> createParcelableNavType(
    isNullableAllowed: Boolean = false
): NavType<T> {
    return object : NavType<T>(isNullableAllowed) {
        override val name: String
            get() = "SupportParcelable"

        override fun put(bundle: Bundle, key: String, value: T) {
            bundle.putParcelable(key, value)
        }

        override fun get(bundle: Bundle, key: String): T? {
            return bundle.getParcelable(key)
        }

        override fun parseValue(value: String): T {
            return Json.decodeFromString(value)
        }
    }
}
