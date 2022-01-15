package team.nexters.kida.ui.splash

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collect
import team.nexters.kida.util.UiEvent

@Composable
fun SplashScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> {}
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = Color.Red
    ) {}
}
