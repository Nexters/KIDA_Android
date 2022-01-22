package team.nexters.kida.component.shape

import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class TopBubbleShape(private val radius: Float) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {

        val trianglePath = Path().apply {
            reset()
            addRoundRect(RoundRect(0f, 0f, size.width, size.height, radius, radius))
            moveTo(x = (size.width / 2), y = -18f)
            lineTo(x = (size.width / 2) - (21 / 2), y = 0f)
            lineTo(x = (size.width / 2) + (21 / 2), y = 0f)
        }
        return Outline.Generic(path = trianglePath)
    }
}
