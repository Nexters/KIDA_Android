package team.nexters.kida.component

import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class BalloonTailShape: Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            reset()
            moveTo(size.width, size.height / 2f)
            lineTo(6f + size.width - size.height, size.height)
            lineTo(6f + size.width - size.height, 0f)
            close()
        }
        return Outline.Generic(path)
    }
}