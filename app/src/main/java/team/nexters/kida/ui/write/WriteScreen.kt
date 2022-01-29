package team.nexters.kida.ui.write

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.TopAppBar
import team.nexters.kida.R
import team.nexters.kida.ui.theme.Theme
import team.nexters.kida.util.UiEvent

@Composable
fun WriteScreen(
    onPopBackStack: () -> Unit,
    viewModel: WriteViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
                else -> {
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        backgroundColor = Theme.colors.background,
        topBar = {
            TopAppBar(
                title = { Text(text = "Write") },
                backgroundColor = MaterialTheme.colors.background,
                contentPadding = rememberInsetsPaddingValues(
                    insets = LocalWindowInsets.current.statusBars,
                    applyBottom = false,
                ),
                navigationIcon = {
                    IconButton(onClick = { onPopBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            null
                        )
                    }
                }
            )
        }
    ) {
        Column(Modifier.fillMaxSize()) {
            Card(
                modifier = Modifier
                    .padding(20.dp)
                    .weight(1f),
                elevation = 10.dp,
                shape = RoundedCornerShape(10.dp),
                backgroundColor = Color.White
            ) {
                Column(
                    modifier = Modifier.padding(start = 20.dp, end = 30.dp, top = 30.dp)
                ) {
                    todayKeyword(viewModel)
                    Spacer(modifier = Modifier.height(34.dp))
                    TextField(
                        value = viewModel.title,
                        onValueChange = {
                            viewModel.onEvent(WriteEvent.OnTitleChange(it))
                        },
                        placeholder = {
                            Text(text = "제목", fontSize = 20.sp)
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                    Divider(color = Color.LightGray, thickness = 1.dp)
                    TextField(
                        value = viewModel.content,
                        onValueChange = {
                            viewModel.onEvent(WriteEvent.OnContentChange(it))
                        },
                        placeholder = {
                            Text(text = "공백 포함 150자 이내로 써 주세요.")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        singleLine = false)
                }
            }
            Button(
                onClick = { viewModel.onEvent(WriteEvent.OnSaveDiary) },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 55.dp, end = 55.dp, top = 4.dp)
            ) {
                Text(text = "작성하기", fontSize = 18.sp, modifier = Modifier.padding(vertical = 12.dp))
            }
            Spacer(modifier = Modifier.size(32.dp))
        }
    }
}

@Composable
fun todayKeyword(viewModel: WriteViewModel) {
    Row(modifier = Modifier.padding(top = 6.dp))
    {
        Column(
            Modifier.weight(1f)
        ) {
            Text(
                text = "오늘의 키워드",
                fontSize = 16.sp
            )
            Text(
                modifier = Modifier.padding(top = 12.dp),
                text = viewModel.keyword,
                fontSize = 40.sp
            )
        }
        Image(
            painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "emoji",
        )
    }
}