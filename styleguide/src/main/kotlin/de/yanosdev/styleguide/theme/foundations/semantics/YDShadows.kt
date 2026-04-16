@file:YDRevisionIn(implementedAt = "2026-04-16", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.foundations.semantics

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn

@Immutable
internal data class YDShadows(
    val sh0: Dp,
    val sh1: Dp,
    val sh4: Dp,
    val sh8: Dp,
    val sh16: Dp,
)

internal val DefaultYDShadow = YDShadows(
    sh0 = 0.dp,
    sh1 = 1.dp,
    sh4 = 4.dp,
    sh8 = 8.dp,
    sh16 = 16.dp,
)

internal val LocalYDShadows = staticCompositionLocalOf { DefaultYDShadow }