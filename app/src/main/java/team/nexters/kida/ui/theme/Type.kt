package team.nexters.kida.ui.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import team.nexters.kida.R

val gmarketSansFamily = FontFamily(
    Font(R.font.gmarket_sans_light, FontWeight.Light),
    Font(R.font.gmarket_sans_medium, FontWeight.Medium),
    Font(R.font.gmarket_sans_bold, FontWeight.Bold)
)

val notoSansFamily = FontFamily(
    Font(R.font.noto_sans_thin, FontWeight.Thin),
    Font(R.font.noto_sans_light, FontWeight.Light),
    Font(R.font.noto_sans_regular, FontWeight.Normal),
    Font(R.font.noto_sans_regular, FontWeight.SemiBold),
)

/**
 *  Figma 에서 정의된 text style
 */
@Immutable
data class KidaTypography constructor(
    val body1: TextStyle = TextStyle(
        fontFamily = gmarketSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    val display: TextStyle = TextStyle(
        fontFamily = gmarketSansFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
    ),
    val h1: TextStyle = TextStyle(
        fontFamily = gmarketSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 26.sp
    ),
    val h2: TextStyle = TextStyle(
        fontFamily = gmarketSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    ),
    val h3: TextStyle = TextStyle(
        fontFamily = notoSansFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ),
    val title: TextStyle = TextStyle(
        fontFamily = gmarketSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp
    ),
    val contents: TextStyle = TextStyle(
        fontFamily = gmarketSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 22.sp
    ),
    val subcontents: TextStyle = TextStyle(
        fontFamily = gmarketSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    val button: TextStyle = TextStyle(
        fontFamily = gmarketSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp
    ),
    val subbtn: TextStyle = TextStyle(
        fontFamily = gmarketSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    )
)

val LocalKidaTypography = staticCompositionLocalOf {
    KidaTypography()
}

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = gmarketSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)
