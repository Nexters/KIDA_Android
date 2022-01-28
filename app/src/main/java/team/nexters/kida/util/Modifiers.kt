package team.nexters.kida.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

val Int.toDp: Dp
    @Composable get() = with(LocalDensity.current) { this@toDp.toDp() }

val Float.toDp: Dp
    @Composable get() = with(LocalDensity.current) { this@toDp.toDp() }
