package team.nexters.kida.ui.write

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.TopAppBar
import team.nexters.kida.ui.theme.Theme
import team.nexters.kida.util.UiEvent

@Composable
fun WriteScreen(
    onPopBackStack: () -> Unit,
    viewModel: WriteViewModel = hiltViewModel(),
    keyword: String
) {
    val scaffoldState = rememberScaffoldState()
    viewModel.onEvent(WriteEvent.OnKeywordChange(keyword))
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
                title = { Text(text = viewModel.date, style = Theme.typography.header) },
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
                backgroundColor = Theme.colors.white
            ) {
                Column(
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 30.dp)
                ) {
                    TodayKeyword(viewModel)
                    Spacer(modifier = Modifier.height(34.dp))
                    NonInnerPaddingTextField(
                        value = viewModel.title,
                        style = Theme.typography.h2,
                        placeholder = "제목",
                        onValueChange = {
                            viewModel.onEvent(WriteEvent.OnTitleChange(it))
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 18.dp, start = 2.dp, end = 2.dp)
                    )
                    Divider(color = Theme.colors.disabled, thickness = 1.dp)
                    NonInnerPaddingTextField(
                        value = viewModel.content,
                        placeholder = "공백 포함 150자 이내로 써 주세요.",
                        style = Theme.typography.h4,
                        onValueChange = {
                            if (it.length <= 150)
                                viewModel.onEvent(WriteEvent.OnContentChange(it))
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(top = 20.dp, bottom = 20.dp, start = 2.dp, end = 2.dp)
                    )
                }
            }
            val btnDisabled =
                (viewModel.content.isEmpty() || viewModel.title.isEmpty() || viewModel.keyword.isEmpty())
            Button(
                enabled = !btnDisabled,
                onClick = {
                    if (!btnDisabled) {
                        viewModel.onEvent(WriteEvent.OnSaveDiary)
                    }
                },
                shape = RoundedCornerShape(10.dp),
                colors = if (btnDisabled) {
                    ButtonDefaults.buttonColors(
                        backgroundColor = Theme.colors.btnDisabled,
                        contentColor = Theme.colors.disabled
                    )
                } else {
                    ButtonDefaults.buttonColors(
                        backgroundColor = Theme.colors.black,
                        contentColor = Theme.colors.white
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 55.dp, end = 55.dp, top = 4.dp)
            ) {
                Text(
                    "작성하기",
                    modifier = Modifier.padding(12.dp)
                )
            }
            Spacer(modifier = Modifier.size(32.dp))
        }
    }
}

@Composable
fun TodayKeyword(viewModel: WriteViewModel) {
    Column {
        Text(
            text = "뽑은 키워드",
            style = Theme.typography.h3
        )
        Text(
            text = viewModel.keyword,
            style = Theme.typography.h1.copy(
                color = Theme.colors.primary
            )
        )
    }
}

@Composable
fun NonInnerPaddingTextField(
    placeholder: String,
    value: String,
    style: TextStyle,
    onValueChange: (String) -> Unit,
    modifier: Modifier
) {
    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        textStyle = style.copy(color = Theme.colors.darkGray),
        decorationBox = { innerTextField ->
            Row(modifier = Modifier.fillMaxWidth()) {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = Theme.colors.disabled,
                        style = style
                    )
                }
            }
            innerTextField()
        }
    )
}
