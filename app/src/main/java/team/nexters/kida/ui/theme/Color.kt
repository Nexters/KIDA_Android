package team.nexters.kida.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/* color palette */
val Primary = Color(0xFFF66705)
val BtnDisabled = Color(0xFFDFDFDF)
val Disabled = Color(0xFF9C9C9C)
val DarkGray = Color(0xFF535353)
val BackGround = Color(0xFFF8F8F8)

/**
 *  Figma 에서 정의된 system color
 *
 *  example : color = Theme.colors.primary
 */
@Immutable
data class ThemeColors(
    val primary: Color = Primary,
    val white: Color = Color.White,
    val btnDisabled: Color = BtnDisabled,
    val disabled: Color = Disabled,
    val darkGray: Color = DarkGray,
    val black: Color = Color.Black,
    val background: Color = BackGround
)

/* customized colors */
val ThemeColors.splashBackground: Color
    @Composable
    get() = Color(0xFFfdfde3)

val LocalThemeColors = staticCompositionLocalOf {
    ThemeColors()
}
