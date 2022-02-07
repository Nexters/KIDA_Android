package team.nexters.kida.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import team.nexters.kida.ui.theme.Theme
import team.nexters.kida.ui.theme.splashBackground
import team.nexters.kida.util.UiEvent

@Composable
fun SplashScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> {}
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.splashBackground),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(164.dp))
        Box(
            modifier = Modifier
                .background(Theme.colors.btnActive)
                .size(136.dp, 136.dp)

        )
    }
}
