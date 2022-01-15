package team.nexters.kida.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import dagger.hilt.android.AndroidEntryPoint
import team.nexters.kida.data.keyword.KeywordDataSource
import team.nexters.kida.ui.detail.DetailScreen
import team.nexters.kida.ui.keyword.KeywordScreen
import team.nexters.kida.ui.list.ListScreen
import team.nexters.kida.ui.splash.SplashScreen
import team.nexters.kida.ui.theme.Theme
import team.nexters.kida.ui.write.WriteScreen
import team.nexters.kida.util.Routes
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var keywordDataSource: KeywordDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Theme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.SPLASH
                ) {
                    composable(Routes.SPLASH) {
                        SplashScreen(
                            onNavigate = {
                                navController.navigate(
                                    it.route,
                                    navOptions {
                                        popUpTo(Routes.SPLASH) { inclusive = true }
                                    })
                            }
                        )
                    }
                    composable(Routes.KEYWORD) { back ->
                        KeywordScreen(
                            onNavigate = {
                                navController.navigate(
                                    it.route,
                                    navOptions {
                                        popUpTo(Routes.KEYWORD) { inclusive = true }
                                    })
                            }
                        )
                    }
                    composable(Routes.LIST) {
                        ListScreen(
                            onNavigate = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                    composable(
                        route = Routes.WRITE + "?diaryId={diaryId}",
                        arguments = listOf(diaryIdArgument())
                    ) {
                        WriteScreen(
                            onPopBackStack = {
                                navController.popBackStack()
                            }
                        )
                    }
                    composable(
                        route = Routes.DETAIL + "?diaryId={diaryId}",
                        arguments = listOf(diaryIdArgument())
                    ) {
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
        }
    }

    private fun diaryIdArgument() = navArgument(name = "diaryId") {
        type = NavType.IntType
        defaultValue = -1
    }
}
