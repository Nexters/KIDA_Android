package team.nexters.kida.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

private val DarkColorPalette = darkColors()

private val LightColorPalette = lightColors()

@Composable
fun Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    val themeColors = ThemeColors()
    val kidaTypography = KidaTypography()
    CompositionLocalProvider(
        LocalThemeColors provides themeColors,
        LocalKidaTypography provides kidaTypography
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
