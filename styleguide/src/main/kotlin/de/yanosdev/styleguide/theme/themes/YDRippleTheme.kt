@file:YDRevisionIn(implementedAt = "2026-04-16", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.themes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDContentColor
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDRippleAlpha
import de.yanosdev.styleguide.theme.foundations.semantics.YDRippleAlpha

internal interface YDRippleTheme {
    @Composable
    fun defaultColor(): Color

    @Composable
    fun rippleAlpha(): YDRippleAlpha
}

@Immutable
internal object DefaultYDRippleTheme : YDRippleTheme {
    @Composable
    override fun defaultColor() = LocalYDContentColor.current

    @Composable
    override fun rippleAlpha() = LocalYDRippleAlpha.current
}

internal val LocalYDRippleTheme = staticCompositionLocalOf { DefaultYDRippleTheme }
