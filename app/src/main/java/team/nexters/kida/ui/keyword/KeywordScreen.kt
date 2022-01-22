package team.nexters.kida.ui.keyword

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.TopAppBar
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.collect
import team.nexters.kida.component.shape.TopBubbleShape
import team.nexters.kida.ui.theme.Bubble
import team.nexters.kida.ui.theme.Disabled
import team.nexters.kida.ui.theme.Primary
import team.nexters.kida.ui.theme.UnderLineYellow
import team.nexters.kida.util.UiEvent

@Composable
fun KeywordScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: KeywordViewModel = hiltViewModel()
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
        topBar = {
            TopAppBar(
                title = {
                    // TODO today
                    Text(text = "Keyword")
                },
                backgroundColor = MaterialTheme.colors.background,
                contentPadding = rememberInsetsPaddingValues(
                    insets = LocalWindowInsets.current.statusBars,
                    applyBottom = false,
                )
            )
        },
        modifier = Modifier.fillMaxSize()
    ) {
        KeywordScreenContent {
            viewModel.onEvent(KeywordEvent.OnKeywordClick)
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun KeywordScreenContent(
    onClickButton: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) {

        Spacer(modifier = Modifier.size(36.dp))

        KeywordHighlightHeader()
        val pagerState = rememberPagerState()

        // TODO custom width
        HorizontalPagerIndicator(
            modifier = Modifier.padding(top = 20.dp),
            pagerState = pagerState,
            spacing = 6.dp,
            activeColor = Primary,
            inactiveColor = Disabled,
            indicatorHeight = 8.dp,
            indicatorWidth = 8.dp
        )
        Spacer(modifier = Modifier.size(60.dp))
        HorizontalPager(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            count = 6,
            state = pagerState
        ) {
            KeywordPagerItem()
        }

        Spacer(modifier = Modifier.size(24.dp))
        KeywordBubbleButton(
            onClick = { onClickButton() },
            modifier = Modifier
        )

        Spacer(modifier = Modifier.size(40.dp))
    }
}

@Composable
private fun KeywordPagerItem() {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .background(Color.Green)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = "발랄한 직각 요정", color = DarkGray)
        }
    }
}

@Composable
private fun KeywordHighlightHeader() {
    Box {
        Box(
            modifier = Modifier
                .width(158.dp)
                .height(18.dp)
                .background(UnderLineYellow)
                .align(Alignment.BottomStart)
        )

        Column(
            modifier = Modifier.padding(start = 5.dp, bottom = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "오늘의 짝꿍을",
                fontSize = 26.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(top = 8.dp))
            Text(
                text = "느낌충만하게 뽑아봐!",
                fontSize = 26.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

// TODO check background color
@Composable
fun KeywordBubbleButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(IntrinsicSize.Max)
            .clickable { onClick() },
    ) {
        val cornerRadius = with(LocalDensity.current) {
            10.dp.toPx()
        }
        Row(
            modifier = Modifier
                .graphicsLayer {
                    shape = TopBubbleShape(cornerRadius)
                    clip = true
                }
                .background(
                    color = Bubble,
                    shape = TopBubbleShape(cornerRadius)
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "이 친구로 뽑을래?",
                modifier = Modifier
                    .padding(
                        top = 18.dp,
                        bottom = 18.dp,
                        start = 56.dp,
                        end = 56.dp
                    ),
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun KeywordBubbleButtonPreview() {
    KeywordBubbleButton({})
}

@Preview
@Composable
private fun KeywordTitlePreview() {
    KeywordHighlightHeader()
}
