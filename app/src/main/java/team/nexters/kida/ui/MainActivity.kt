package team.nexters.kida.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import team.nexters.kida.ui.list.ListScreen
import team.nexters.kida.ui.theme.Theme
import team.nexters.kida.util.Routes

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Theme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.LIST
                ) {
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

                    }

                    composable(
                        route = Routes.DETAIL + "?diaryId={diaryId}",
                        arguments = listOf(diaryIdArgument())
                    ) {

                    }
                }
            }
        }
    }

    private fun diaryIdArgument() = navArgument(name = "diaryId") {
        type = NavType.IntType,
        defaultValue = -1
    }
}
