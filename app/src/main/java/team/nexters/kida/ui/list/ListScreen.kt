package team.nexters.kida.ui.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.TopAppBar
import kotlinx.coroutines.flow.collect
import team.nexters.kida.util.UiEvent

@Composable
fun ListScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: ListViewModel = hiltViewModel()
) {
    val diaries = viewModel.diaries.collectAsState(initial = emptyList())
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
        topBar = {
            TopAppBar(
                title = { Text(text = "List") },
                backgroundColor = MaterialTheme.colors.background,
                contentPadding = rememberInsetsPaddingValues(
                    insets = LocalWindowInsets.current.statusBars,
                    applyBottom = false,
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.navigationBarsPadding(),
                onClick = {
                    viewModel.onEvent(ListEvent.OnWriteClick)
                }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    "Add"
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(diaries.value) { diary ->
                DiaryItem(
                    diary = diary,
                    onEvent = viewModel::onEvent,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}
