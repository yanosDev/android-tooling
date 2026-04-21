@file:YDRevisionIn(implementedAt = "2026-04-16", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.foundations.semantics

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import de.yanosdev.annotation.YDRevisionIn

@Immutable
data class YDFontSizes(
    val extraTiny: TextUnit,
    val tiny: TextUnit,
    val small: TextUnit,
    val medium: TextUnit,
    val large: TextUnit,
    val big: TextUnit,
    val huge: TextUnit,
    val extraHuge: TextUnit,
    val gigantic: TextUnit,
)

@Stable
internal val DefaultYDFontSizes = YDFontSizes(
    extraTiny = 8.sp,
    tiny = 10.sp,
    small = 12.sp,
    medium = 14.sp,
    large = 16.sp,
    big = 18.sp,
    huge = 20.sp,
    extraHuge = 24.sp,
    gigantic = 30.sp
)

internal val LocalYDFontSizes = staticCompositionLocalOf { DefaultYDFontSizes }