@file:YDRevisionIn(implementedAt = "2026-04-16", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.foundations.locals

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.foundations.token.YDTypographyTokens

val LocalYDTextStyle = compositionLocalOf {
    YDTypographyTokens.mdRegular.textStyle(isCompact = true)
}

@Composable
fun ProvideMergedYDTextStyle(value: TextStyle, content: @Composable () -> Unit) {
    val mergedStyle = LocalYDTextStyle.current.merge(value)
    CompositionLocalProvider(LocalYDTextStyle provides mergedStyle, content = content)
}