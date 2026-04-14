package de.yanosdev.styleguide.theme.foundations.spacing

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn

@Immutable
@YDRevisionIn(implementedAt = "2026", revisionAfterInDays = 365)
data class YDSpacing(
    val b0: Dp,
    val b1: Dp,
    val b2: Dp,
    val b4: Dp,
    val b8: Dp,
    val b12: Dp,
    val b16: Dp,
    val b24: Dp,
    val b32: Dp,
    val b40: Dp,
    val b48: Dp,
    val b56: Dp,
    val b64: Dp,
    val b72: Dp,
    val b80: Dp,
    val b88: Dp,
    val b96: Dp,
    val b104: Dp,
    val b112: Dp,
    val b120: Dp,
    val b128: Dp,
    val b136: Dp,
    val b144: Dp
)

@Stable
internal val DefaultC24Grid = YDSpacing(
    b0 = 0.dp,
    b1 = 1.dp,
    b2 = 2.dp,
    b4 = 4.dp,
    b8 = 8.dp,
    b12 = 12.dp,
    b16 = 16.dp,
    b24 = 24.dp,
    b32 = 32.dp,
    b40 = 40.dp,
    b48 = 48.dp,
    b56 = 56.dp,
    b64 = 64.dp,
    b72 = 72.dp,
    b80 = 80.dp,
    b88 = 88.dp,
    b96 = 96.dp,
    b104 = 104.dp,
    b112 = 112.dp,
    b120 = 120.dp,
    b128 = 128.dp,
    b136 = 136.dp,
    b144 = 144.dp
)

internal val LocalC24Grid = staticCompositionLocalOf { DefaultC24Grid }