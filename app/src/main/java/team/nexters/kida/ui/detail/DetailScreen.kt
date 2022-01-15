package team.nexters.kida.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collect
import team.nexters.kida.ui.theme.Typography
import team.nexters.kida.util.UiEvent

@Composable
fun DetailScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    onPopBackStack: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val diary = viewModel.diary.collectAsState(initial = null)
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                is UiEvent.PopBackStack -> onPopBackStack()
                else -> {}
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = diary.value?.title.orEmpty(),
                style = Typography.h4
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = diary.value?.content.orEmpty(),
                style = Typography.body1
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = diary.value?.keyword.orEmpty(),
                style = Typography.body2
            )
        }
    }
}
