package team.nexters.kida.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 *  Figma 에서 정의된 system color
 *
 *  example : color = Theme.colors.primary
 */
@Immutable
data class ThemeColors(
    /* BG */
    val background: Color = Color(0xFF252525),
    val popup: Color = Color(0xFF323232),
    val bgLayered: Color = Color(0xFF303030),
    val bgLayered2: Color = Color(0xFF3D3D3D),
    /* BTN */
    val btnActive: Color = Color(0xFFF66705),
    val btnDefault: Color = Color.White,
    val btnDefault2: Color = Color(0xFFA6A6A6),
    val btnDisabled: Color = Color(0xFF646464),
    val btnIndicatorInActive: Color = Color(0xFF5A5A5A),
    /* TEXT */
    val textDefault: Color = Color.White,
    val textContent: Color = Color(0xFFC9C9C9),
    val btnRe: Color = Color(0xFF868686),
    val placeholderInactive: Color = Color(0xFF9D9D9D),
    val label: Color = Color(0xFF7D7D7D),
    val label2: Color = Color(0xFFFF964E),
)

/* customized colors */
val ThemeColors.splashBackground: Color
    @Composable
    get() = Color(0xFFfdfde3)

val LocalThemeColors = staticCompositionLocalOf {
    ThemeColors()
}
