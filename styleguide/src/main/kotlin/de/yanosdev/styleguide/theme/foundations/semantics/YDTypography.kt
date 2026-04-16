@file:YDRevisionIn(implementedAt = "2026-04-16", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.foundations.semantics

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.foundations.token.YDTypographyTokens

@Immutable
internal data class YDTypography(
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
    h1 = YDTypographyTokens.h1.textStyle(isCompact),
    h2 = YDTypographyTokens.h2.textStyle(isCompact),
    h3 = YDTypographyTokens.h3.textStyle(isCompact),
    h4 = YDTypographyTokens.h4.textStyle(isCompact),
    h5 = YDTypographyTokens.h5.textStyle(isCompact),
    lgMediumBold = YDTypographyTokens.lgMediumBold.textStyle(isCompact),
    lgRegular = YDTypographyTokens.lgRegular.textStyle(isCompact),
    mdMediumBold = YDTypographyTokens.mdMediumBold.textStyle(isCompact),
    mdRegular = YDTypographyTokens.mdRegular.textStyle(isCompact),
    smMediumBold = YDTypographyTokens.smMediumBold.textStyle(isCompact),
    smRegular = YDTypographyTokens.smRegular.textStyle(isCompact),
    xsMediumBold = YDTypographyTokens.xsMediumBold.textStyle(isCompact),
    xsRegular = YDTypographyTokens.xsRegular.textStyle(isCompact),
)

internal val LocalYDTypography = staticCompositionLocalOf { CompactYDTypography }