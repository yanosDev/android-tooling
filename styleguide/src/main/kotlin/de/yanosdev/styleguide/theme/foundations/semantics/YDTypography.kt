@file:YDRevisionIn(implementedAt = "2026-04-16", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.foundations.semantics

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.foundations.token.YDTypographyTokens

@Immutable
data class YDTypography(
    val h1: TextStyle,
    val h2: TextStyle,
    val h3: TextStyle,
    val h4: TextStyle,
    val h5: TextStyle,
    val lgMediumBold: TextStyle,
    val lgRegular: TextStyle,
    val mdMediumBold: TextStyle,
    val mdRegular: TextStyle,
    val smMediumBold: TextStyle,
    val smRegular: TextStyle,
    val xsMediumBold: TextStyle,
    val xsRegular: TextStyle,
)

@Stable
internal val CompactYDTypography = defaultYDTypography(isCompact = true)

@Stable
internal val ExpandedYDTypography = defaultYDTypography(isCompact = false)

@Stable
private fun defaultYDTypography(isCompact: Boolean) = YDTypography(
    h1 = YDTypographyTokens.h1.textStyle(isCompact = isCompact),
    h2 = YDTypographyTokens.h2.textStyle(isCompact = isCompact),
    h3 = YDTypographyTokens.h3.textStyle(isCompact = isCompact),
    h4 = YDTypographyTokens.h4.textStyle(isCompact = isCompact),
    h5 = YDTypographyTokens.h5.textStyle(isCompact = isCompact),
    lgMediumBold = YDTypographyTokens.lgMediumBold.textStyle(isCompact = isCompact),
    lgRegular = YDTypographyTokens.lgRegular.textStyle(isCompact = isCompact),
    mdMediumBold = YDTypographyTokens.mdMediumBold.textStyle(isCompact = isCompact),
    mdRegular = YDTypographyTokens.mdRegular.textStyle(isCompact = isCompact),
    smMediumBold = YDTypographyTokens.smMediumBold.textStyle(isCompact = isCompact),
    smRegular = YDTypographyTokens.smRegular.textStyle(isCompact = isCompact),
    xsMediumBold = YDTypographyTokens.xsMediumBold.textStyle(isCompact = isCompact),
    xsRegular = YDTypographyTokens.xsRegular.textStyle(isCompact = isCompact),
)

internal val LocalYDTypography = staticCompositionLocalOf { CompactYDTypography }

internal val LocalYDTextStyle = compositionLocalOf {
    YDTypographyTokens.mdRegular.textStyle(isCompact = true)
}

@Composable
fun ProvideMergedYDTextStyle(value: TextStyle, content: @Composable () -> Unit) {
    val mergedStyle = LocalYDTextStyle.current.merge(value)
    CompositionLocalProvider(LocalYDTextStyle provides mergedStyle, content = content)
}