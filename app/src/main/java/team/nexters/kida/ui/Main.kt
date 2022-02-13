package team.nexters.kida.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun Main() {
    val navController = rememberNavController()
    val systemUi = rememberSystemUiController()

    val useDarkIcons = MaterialTheme.colors.isLight
    SideEffect {
        systemUi.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = useDarkIcons
        )
        systemUi.setNavigationBarColor(
            color = Color.Transparent,
            darkIcons = useDarkIcons
        )
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        AppNavGraph(navController = navController)
    }
}
