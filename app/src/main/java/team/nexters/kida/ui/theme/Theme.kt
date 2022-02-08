package team.nexters.kida.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun Theme(
    content: @Composable () -> Unit
) {
    // no support light theme
    val colors = darkColors()

    val themeColors = ThemeColors()
    val typography = KidaTypography()
    CompositionLocalProvider(
        LocalThemeColors provides themeColors,
        LocalKidaTypography provides typography
    ) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

object Theme {
    val colors: ThemeColors
        @Composable
        get() = LocalThemeColors.current
    val typography: KidaTypography
        @Composable
        get() = LocalKidaTypography.current
}
