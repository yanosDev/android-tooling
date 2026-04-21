@file:YDRevisionIn(implementedAt = "2026-04-15", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.foundations.semantics

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn

@Immutable
data class YDRadius(
    val zero: Dp,
    val extraTiny: Dp,
    val tiny: Dp,
    val small: Dp,
    val medium: Dp,
    val large: Dp,
    val big: Dp,
    val huge: Dp,
)

@Stable
internal val DefaultYDRadius = YDRadius(
    zero = 0.dp,
    extraTiny = 1.dp,
    tiny = 2.dp,
    small = 4.dp,
    medium = 8.dp,
    large = 12.dp,
    big = 16.dp,
    huge = 32.dp
)

internal val LocalYDRadius = staticCompositionLocalOf { DefaultYDRadius }