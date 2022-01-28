package team.nexters.kida.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import team.nexters.kida.util.toDp

@ExperimentalPagerApi
@Composable
fun HorizontalPagerIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    activeColor: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
    activeIndicatorWidth: Dp = 8.dp,
    activeIndicatorHeight: Dp = activeIndicatorWidth,
    inactiveColor: Color = activeColor.copy(ContentAlpha.disabled),
    inactiveIndicatorWidth: Dp = 8.dp,
    inactiveIndicatorHeight: Dp = inactiveIndicatorWidth,
    spacing: Dp = inactiveIndicatorWidth,
    indicatorShape: Shape = CircleShape,
) {
    val activeIndicatorWidthPx = LocalDensity.current.run { activeIndicatorWidth.roundToPx() }
    val inactiveIndicatorWidthPx = LocalDensity.current.run { inactiveIndicatorWidth.roundToPx() }
    val spacingPx = LocalDensity.current.run { spacing.roundToPx() }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            val activeSpacing = ((activeIndicatorWidthPx - inactiveIndicatorWidthPx) / 2f)
            repeat(pagerState.pageCount) {

                val start = if (it == 0) activeSpacing else (spacingPx / 2) + (activeSpacing / 2)
                val end = if (it == pagerState.pageCount - 1) activeSpacing else (spacingPx / 2) + (activeSpacing / 2)
                Box(
                    Modifier
                        .padding(start = start.toDp, end = end.toDp)
                        .size(width = inactiveIndicatorWidth, height = inactiveIndicatorHeight)
                        .background(color = inactiveColor, shape = indicatorShape)
                )
            }
        }

        Box(
            Modifier
                .offset {
                    val scrollPosition = (pagerState.currentPage + pagerState.currentPageOffset)
                        .coerceIn(
                            0f,
                            (pagerState.pageCount - 1)
                                .coerceAtLeast(0)
                                .toFloat()
                        )
                    IntOffset(
                        x = ((((activeIndicatorWidthPx - inactiveIndicatorWidthPx) / 2) + inactiveIndicatorWidthPx + spacingPx) * scrollPosition).toInt(),
                        y = 0
                    )
                }
                .size(width = activeIndicatorWidth, height = activeIndicatorHeight)
                .background(
                    color = activeColor,
                    shape = indicatorShape
                )

        )
    }
}
