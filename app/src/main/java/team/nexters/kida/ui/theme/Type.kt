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

val notoSansFamily = FontFamily(
    Font(R.font.noto_sans_regular, FontWeight.Medium),
    Font(R.font.noto_sans_medium, FontWeight.SemiBold),
    Font(R.font.noto_sans_bold, FontWeight.Bold)
)

/**
 *  Figma 에서 정의된 text style
 */
@Immutable
data class KidaTypography constructor(
    val header: TextStyle = TextStyle(
        fontFamily = notoSansFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
    ),
    val display: TextStyle = TextStyle(
        fontFamily = notoSansFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
    ),
    val h1: TextStyle = TextStyle(
        fontFamily = notoSansFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    val h2: TextStyle = TextStyle(
        fontFamily = notoSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    val h3: TextStyle = TextStyle(
        fontFamily = notoSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    ),
    val h4: TextStyle = TextStyle(
        fontFamily = notoSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 24.sp
    ),
    val body1: TextStyle = TextStyle(
        fontFamily = notoSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    val btn: TextStyle = TextStyle(
        fontFamily = notoSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    val btn_re: TextStyle = TextStyle(
        fontFamily = notoSansFamily,
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
        fontFamily = notoSansFamily,
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
