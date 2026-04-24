@file:YDRevisionIn(implementedAt = "2026-04-16", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.foundations.semantics

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn

@Immutable
data class YDSizes(
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
private val DefaultYDSize = YDSizes(
    extraTiny = 16.dp,
    tiny = 24.dp,
    small = 32.dp,
    medium = 48.dp,
    large = 56.dp,
    big = 64.dp,
    huge = 72.dp,
    extraHuge = 256.dp
)

internal val LocalYDSizes = staticCompositionLocalOf { DefaultYDSize }