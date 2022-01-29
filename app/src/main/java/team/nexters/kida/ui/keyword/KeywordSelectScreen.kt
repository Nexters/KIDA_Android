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
import androidx.compose.ui.platform.LocalContext
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
import com.google.accompanist.pager.rememberPagerState
import team.nexters.kida.R
import team.nexters.kida.component.HorizontalPagerIndicator
import team.nexters.kida.component.shape.TopBubbleShape
import team.nexters.kida.ui.theme.Theme
import team.nexters.kida.ui.theme.underlineYellow
import team.nexters.kida.util.DateUtils
import team.nexters.kida.util.UiEvent

@Composable
fun KeywordSelectScreen(
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
    val pagerSubtitles = with(LocalContext.current) {
        listOf(
            getString(R.string.keyword_pager_1),
            getString(R.string.keyword_pager_2),
            getString(R.string.keyword_pager_3),
            getString(R.string.keyword_pager_4),
            getString(R.string.keyword_pager_5),
            getString(R.string.keyword_pager_6)
        )
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = DateUtils.today()) },
                backgroundColor = MaterialTheme.colors.background,
                contentPadding = rememberInsetsPaddingValues(
                    insets = LocalWindowInsets.current.statusBars,
                    applyBottom = false,
                )
            )
        },
        modifier = Modifier.fillMaxSize()
    ) {
        KeywordSelectContent(
            onClickButton = { viewModel.onEvent(KeywordEvent.OnKeywordClick) },
            pagerSubtitles
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun KeywordSelectContent(
    onClickButton: () -> Unit,
    pagerSubTitles: List<String>
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

        HorizontalPagerIndicator(
            modifier = Modifier.padding(top = 20.dp),
            pagerState = pagerState,
            spacing = 6.dp,
            activeColor = Theme.colors.primary,
            activeIndicatorWidth = 24.dp,
            activeIndicatorHeight = 8.dp,
            inactiveColor = Theme.colors.disabled,
            inactiveIndicatorHeight = 8.dp,
            inactiveIndicatorWidth = 8.dp
        )
        Spacer(modifier = Modifier.size(60.dp))
        HorizontalPager(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            count = 6,
            state = pagerState
        ) {
            KeywordPagerItem(pagerSubTitles[pagerState.currentPage])
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
private fun KeywordPagerItem(subTitle: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .background(Color.Green)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = subTitle, color = DarkGray)
    }
}

@Composable
private fun KeywordHighlightHeader() {
    val context = LocalContext.current
    Box {
        Box(
            modifier = Modifier
                .width(158.dp)
                .height(18.dp)
                .background(Theme.colors.underlineYellow)
                .align(Alignment.BottomStart)
        )

        Column(
            modifier = Modifier.padding(start = 5.dp, bottom = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = context.getString(R.string.keyword_highlight_1),
                fontSize = 26.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(top = 8.dp))
            Text(
                text = context.getString(R.string.keyword_highlight_2),
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
                    color = Color.Black,
                    shape = TopBubbleShape(cornerRadius)
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = with(LocalContext.current) { getString(R.string.keyword_select_confirm) },
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
