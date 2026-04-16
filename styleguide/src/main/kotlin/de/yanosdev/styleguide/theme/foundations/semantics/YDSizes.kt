@file:YDRevisionIn(implementedAt = "2026-04-16", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.foundations.semantics

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn

@Immutable
internal data class YDSizes(
    val s16: Dp,
    val s24: Dp,
    val s32: Dp,
    val s40: Dp,
    val s48: Dp,
    val s56: Dp,
    val s64: Dp,
    val s72: Dp,
    val s126: Dp,
    val s156: Dp,
    val s256: Dp
)

internal val DefaultYDSize = YDSizes(
    s16 = 16.dp,
    s24 = 24.dp,
    s32 = 32.dp,
    s40 = 40.dp,
    s48 = 48.dp,
    s56 = 56.dp,
    s64 = 64.dp,
    s72 = 72.dp,
    s126 = 126.dp,
    s156 = 156.dp,
    s256 = 256.dp,
)

internal val LocalYDSizes = staticCompositionLocalOf { DefaultYDSize }