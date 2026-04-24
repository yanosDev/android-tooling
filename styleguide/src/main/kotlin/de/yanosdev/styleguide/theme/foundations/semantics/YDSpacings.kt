@file:YDRevisionIn(implementedAt = "2026-04-14", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.foundations.semantics

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn

@Immutable
data class YDSpacings(
    val zero: Dp,
    val extraTiny: Dp,
    val tiny: Dp,
    val small: Dp,
    val medium: Dp,
    val large: Dp,
    val big: Dp,
    val huge: Dp,
    val extraHuge: Dp,
)

@Stable
private val DefaultYDSpacings = YDSpacings(
    zero = 0.dp,
    extraTiny = 2.dp,
    tiny = 4.dp,
    small = 8.dp,
    medium = 12.dp,
    large = 16.dp,
    big = 20.dp,
    huge = 24.dp,
    extraHuge = 56.dp,
)

internal val LocalYDSpacings = staticCompositionLocalOf { DefaultYDSpacings }