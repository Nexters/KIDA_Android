package team.nexters.kida.ui.list

import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.TopAppBar
import kotlinx.coroutines.flow.collect
import team.nexters.kida.ui.theme.BackGround
import team.nexters.kida.ui.theme.Black
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
                title = { Text(text = "내 일기") },
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
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    "Add"
                )
            }
        },
        backgroundColor = BackGround
    ) {
       Column(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 30.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "내가 쓴 일기",
                    style = TextStyle(
                        color = Black,
                        fontSize = 20.sp
                    )
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
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
}
