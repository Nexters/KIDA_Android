package team.nexters.kida.ui.keyword

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import team.nexters.kida.R
import team.nexters.kida.component.CenterAppBar
import team.nexters.kida.component.HorizontalPagerIndicator
import team.nexters.kida.data.keyword.Keyword
import team.nexters.kida.ui.theme.Theme
import team.nexters.kida.ui.theme.notoSansFamily
import team.nexters.kida.util.DateUtils
import kotlin.math.absoluteValue

@Composable
fun KeywordSelectScreen(
    onNavigate: (Keyword, KeywordCard) -> Unit,
    viewModel: KeywordViewModel = hiltViewModel(),
    onInfoClick: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val keywords by viewModel.keywords.collectAsState()

    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = Theme.colors.background,
        topBar = {
            CenterAppBar(
                title = {
                    Text(
                        text = DateUtils.today(),
                        color = Theme.colors.textDefault, fontSize = 14.sp
                    )
                },
                backgroundColor = Theme.colors.background,
                contentPadding = rememberInsetsPaddingValues(
                    insets = LocalWindowInsets.current.statusBars,
                    applyBottom = false,
                ),
                actions = {
                    IconButton(
                        modifier = Modifier.wrapContentSize(),
                        onClick = onInfoClick
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_info),
                            contentDescription = null,
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        modifier = Modifier.wrapContentSize(),
                        onClick = {}
                    ) {
                        Image(
                            modifier = Modifier.size(width = 30.dp, height = 14.dp),
                            painter = painterResource(R.drawable.icon),
                            contentDescription = null,
                        )
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) {
        KeywordSelectContent(
            onClickButton = {
                // TODO filtering
                onNavigate(keywords.random(), it)
            },
            keywords
        )
    }
}

@Composable
private fun KeywordSelectContent(
    onClickButton: (KeywordCard) -> Unit,
    keywords: List<Keyword>
) {
    val pagerState = rememberPagerState()
    var selectedItemPosition by remember { mutableStateOf(-1) }
    var confirmButtonEnabled by remember { mutableStateOf(false) }

    // update confirm button state
    LaunchedEffect(selectedItemPosition) {
        confirmButtonEnabled = selectedItemPosition != -1
    }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) {

        Spacer(modifier = Modifier.size(12.dp))

        KeywordSelectHeader(
            buttonEnabled = confirmButtonEnabled,
            pagerState = pagerState,
            onConfirmClick = { onClickButton(KeywordCard.values()[selectedItemPosition]) }
        )
        Spacer(modifier = Modifier.size(20.dp))
        HorizontalPager(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            count = KeywordCard.values().size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 40.dp),
        ) { page ->
            // Calculate the absolute offset for the current page from the
            // scroll position. We use the absolute value which allows us to mirror
            // any effects for both directions
            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

            // clear selected page
            if (pagerState.isScrollInProgress) {
                selectedItemPosition = -1
            }
            KeywordSelectPagerCardItem(
                page = page,
                pageOffset = pageOffset,
                animatePosition = selectedItemPosition,
                onClick = {
                    selectedItemPosition = it
                }
            )
        }

        Spacer(modifier = Modifier.size(24.dp))
    }
}

@Composable
fun KeywordSelectHeader(
    buttonEnabled: Boolean,
    pagerState: PagerState,
    onConfirmClick: () -> Unit
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(horizontal = 40.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = context.getString(R.string.keyword_highlight_1),
                fontSize = 30.sp,
                textAlign = TextAlign.Start,
                color = Theme.colors.textDefault
            )

            HorizontalPagerIndicator(
                modifier = Modifier.padding(top = 20.dp),
                pagerState = pagerState,
                spacing = 6.dp,
                activeColor = Theme.colors.btnActive,
                activeIndicatorWidth = 18.dp,
                activeIndicatorHeight = 6.dp,
                inactiveColor = Theme.colors.btnIndicatorInActive,
                inactiveIndicatorHeight = 6.dp,
                inactiveIndicatorWidth = 6.dp
            )
        }

        KeywordSelectConfirmButton(
            onClick = onConfirmClick,
            modifier = Modifier.align(Alignment.BottomEnd),
            enabled = buttonEnabled
        )
    }
}

@Preview
@Composable
fun KeywordSelectHeaderPreview() {
    KeywordSelectHeader(true, pagerState = rememberPagerState(), onConfirmClick = {})
}

@Composable
fun KeywordSelectPagerCardItem(
    page: Int,
    pageOffset: Float,
    animatePosition: Int,
    onClick: (Int) -> Unit
) {
    var clicked by remember { mutableStateOf(false) }

    LaunchedEffect(animatePosition) {
        clicked = animatePosition != -1
    }
    val canAnimated = animatePosition == -1 || animatePosition == page
    val animatePadding by animateDpAsState(targetValue = if (canAnimated && clicked) (-10).dp else 0.dp)

    Image(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                onClick = {
                    clicked = !clicked
                    onClick(if (clicked) page else -1)
                },
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            )
            .offset(y = animatePadding)
            .graphicsLayer {

                // We animate the scaleX + scaleY, between 85% and 100%
                lerp(
                    start = 0.85f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                ).also { scale ->
                    scaleX = scale
                    scaleY = scale
                }

                // We animate the alpha, between 50% and 100%
                alpha = lerp(
                    start = 0.5f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                )
            },
        painter = painterResource(id = KeywordCard.values()[page].resId),
        contentDescription = null
    )
}

@Composable
fun KeywordSelectConfirmButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val backgroundColors by animateColorAsState(targetValue = if (enabled) Theme.colors.btnActive else Theme.colors.btnDefault)
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End
    ) {
        AnimatedVisibility(
            visible = enabled,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            KeywordSelectSnackbar(modifier = Modifier.padding(bottom = 10.dp))
        }

        Box(
            modifier = modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(backgroundColors)
                .clickable(
                    onClick = onClick,
                    enabled = enabled,
                    role = Role.Button,
                    interactionSource = interactionSource,
                    indication = null
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow),
                contentDescription = "confirm",
                tint = Theme.colors.background
            )
        }
    }
}

@Composable
fun KeywordSelectSnackbar(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Box(
        modifier = modifier
            .wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier,
            imageVector = ImageVector.vectorResource(id = R.drawable.keyword_snackbar),
            contentDescription = null
        )
        Text(
            text = context.getString(R.string.keyword_select_snackbar),
            modifier = Modifier
                .align(Alignment.Center),
            style = TextStyle(
                fontFamily = notoSansFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = with(LocalDensity.current) {
                    12.dp.toSp()
                }
            ),
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}

@Preview
@Composable
fun KeywordSelectSnackbarPreview() {
    KeywordSelectSnackbar()
}

@Preview
@Composable
fun KeywordSelectConfirmButtonEnablePreview() {
    KeywordSelectConfirmButton(onClick = { })
}

@Preview
@Composable
fun KeywordSelectConfirmButtonDisabledPreview() {
    KeywordSelectConfirmButton(onClick = { }, enabled = false)
}
