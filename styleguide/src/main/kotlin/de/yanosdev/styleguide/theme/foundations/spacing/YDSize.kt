@file:YDRevisionIn(implementedAt = "2026-04-16", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.foundations.spacing

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import de.yanosdev.annotation.YDRevisionIn

@Immutable
data class YDSize(
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