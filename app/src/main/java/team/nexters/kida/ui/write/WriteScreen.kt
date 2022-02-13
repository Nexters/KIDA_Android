package team.nexters.kida.ui.write

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.rememberInsetsPaddingValues
import team.nexters.kida.R
import team.nexters.kida.component.CenterAppBar
import team.nexters.kida.data.keyword.Keyword
import team.nexters.kida.ui.Screen
import team.nexters.kida.ui.theme.Theme
import team.nexters.kida.util.DateUtils
import team.nexters.kida.util.UiEvent

@Composable
fun WriteScreen(
    onPopBackStack: () -> Unit,
    onNavigateToList: () -> Unit,
    viewModel: WriteViewModel = hiltViewModel(),
    keyword: Keyword
) {
    val scaffoldState = rememberScaffoldState()
    if (keyword.name.isNotBlank())
        viewModel.onEvent(WriteEvent.OnKeywordChange(keyword.name))
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
                is UiEvent.Navigate -> {
                    if (event.route == Screen.List.route) {
                        onNavigateToList()
                    }
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
            CenterAppBar(
                title = {
                    Text(
                        text = DateUtils.todayDate(viewModel.date),
                        style = Theme.typography.header
                    )
                },
                backgroundColor = Theme.colors.background,
                contentPadding = rememberInsetsPaddingValues(
                    insets = LocalWindowInsets.current.statusBars,
                    applyBottom = false,
                ),
                navigationIcon = {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(width = 30.dp, height = 14.dp),
                        painter = painterResource(R.drawable.icon),
                        contentDescription = null,
                    )
                },
                actions = {
                    IconButton(
                        modifier = Modifier.wrapContentSize(),
                        onClick = { onPopBackStack() }
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_closed),
                            contentDescription = null
                        )
                    }
                },
            )
        }
    ) {
        val context = LocalContext.current

        Column(Modifier.fillMaxSize()) {
            writeUI(viewModel)

            Spacer(modifier = Modifier.size(20.dp))
            val btnDisabled = if (viewModel.isWriteMode) {
                (viewModel.content.isEmpty() || viewModel.title.isEmpty() || viewModel.keyword.isEmpty())
            } else {
                viewModel.title == viewModel.preTitle && viewModel.content == viewModel.preContent
            }

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
                        contentColor = Theme.colors.placeholderInactive
                    )
                } else {
                    ButtonDefaults.buttonColors(
                        backgroundColor = Theme.colors.btnActive,
                        contentColor = Theme.colors.textDefault
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 55.dp)
            ) {
                Text(
                    if (viewModel.isWriteMode) context.getString(R.string.write_complete_btn) else context.getString(
                        R.string.write_modify_btn
                    ),
                    modifier = Modifier.padding(16.dp)
                )
            }
            Spacer(modifier = Modifier.size(32.dp))
        }
    }
}

@Composable
fun TodayKeyword(viewModel: WriteViewModel) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.15f),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val context = LocalContext.current

        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(R.drawable.particle),
                contentDescription = null
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = context.getString(R.string.write_keyword),
                    fontSize = 12.sp,
                    color = Theme.colors.label2
//            style = Theme.typography.h3.copy(color = Theme.colors.label2)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = viewModel.keyword,
                    fontWeight = FontWeight.ExtraBold,
//            style = Theme.typography.h1,
                    fontSize = 24.sp
                )
            }
        }
    }
}

// @Preview
@Composable
fun writeUI(viewModel: WriteViewModel) {
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .background(color = Theme.colors.bgLayered, shape = RoundedCornerShape(10.dp))
    ) {
        val context = LocalContext.current

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            TodayKeyword(viewModel)
            Spacer(modifier = Modifier.height(24.dp))
            InputBox(
                value = viewModel.title,
                label = context.getString(R.string.write_title),
                placeholder = context.getString(R.string.write_title_constrict),
                contentSize = 16,
                onValueChange = {
                    if (it.length <= 20) viewModel.onEvent(WriteEvent.OnTitleChange(it))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(
                        FocusDirection.Down
                    )
                })
            )
            Spacer(modifier = Modifier.height(12.dp))
            InputBox(
                value = viewModel.content,
                label = context.getString(R.string.write_contents),
                placeholder = context.getString(R.string.write_contents_constrict),
                contentSize = 14,
                onValueChange = {
                    if (it.length <= 150) viewModel.onEvent(WriteEvent.OnContentChange(it))
                },
                modifier = Modifier
                    .fillMaxSize()
                    .focusRequester(focusRequester),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() })
            )
        }
    }
}

@Composable
fun InputBox(
    value: String,
    label: String,
    placeholder: String,
    modifier: Modifier,
    contentSize: Int,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Theme.colors.bgLayered2, shape = RoundedCornerShape(10.dp))
    ) {
        Column(
            Modifier.padding(20.dp)
        ) {
            Text(text = label, fontSize = 12.sp)
            Spacer(Modifier.height(8.dp))
            BasicTextField(
                modifier = modifier,
                value = value,
                textStyle = TextStyle(
                    fontSize = contentSize.sp,
                    lineHeight = 24.sp,
                    color = Theme.colors.textDefault,
                ),
                onValueChange = onValueChange,
                decorationBox = { innerTextField ->
                    Row(modifier = Modifier.fillMaxWidth()) {
                        if (value.isEmpty()) {
                            Text(
                                text = placeholder,
                                fontSize = contentSize.sp,
                                color = Theme.colors.label
                            )
                        }
                    }
                    innerTextField()
                },
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions
            )
        }
    }
}
