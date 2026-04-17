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
    val r0: Dp,
    val r1: Dp,
    val r2: Dp,
    val r4: Dp,
    val r8: Dp,
    val r12: Dp,
    val r16: Dp,
    val r32: Dp
)

@Stable
internal val DefaultYDRadius = YDRadius(
    r0 = 0.dp,
    r1 = 1.dp,
    r2 = 2.dp,
    r4 = 4.dp,
    r8 = 8.dp,
    r12 = 12.dp,
    r16 = 16.dp,
    r32 = 32.dp
)

internal val LocalYDRadius = staticCompositionLocalOf { DefaultYDRadius }