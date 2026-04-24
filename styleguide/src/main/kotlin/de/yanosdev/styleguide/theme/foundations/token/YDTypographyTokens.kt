@file:YDRevisionIn(implementedAt = "2026-04-16", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.foundations.token

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.R
import de.yanosdev.styleguide.theme.foundations.semantics.DefaultYDFontSizes

internal object YDTypographyTokens {
    val h1 = HeadlineToken(
        compactFontSize = DefaultYDFontSizes.gigantic,
    )
    val h2 = HeadlineToken(
        compactFontSize = DefaultYDFontSizes.extraHuge,
    )
    val h3 = HeadlineToken(
        compactFontSize = DefaultYDFontSizes.huge,
    )
    val h4 = HeadlineToken(
        compactFontSize = DefaultYDFontSizes.big,
    )
    val h5 = HeadlineToken(
        compactFontSize = DefaultYDFontSizes.large,
    )
    val lgMediumBold = TextToken(
        fontSize = DefaultYDFontSizes.large,
        fontWeight = FontWeight.SemiBold,
    )
    val lgRegular = TextToken(
        fontSize = DefaultYDFontSizes.large,
    )
    val mdMediumBold = TextToken(
        fontSize = DefaultYDFontSizes.medium,
        fontWeight = FontWeight.SemiBold,
    )
    val mdRegular = TextToken(
        fontSize = DefaultYDFontSizes.medium,
    )
    val smMediumBold = TextToken(
        fontSize = DefaultYDFontSizes.small,
        fontWeight = FontWeight.SemiBold,
    )
    val smRegular = TextToken(
        fontSize = DefaultYDFontSizes.small,
    )
    val xsMediumBold = TextToken(
        fontSize = DefaultYDFontSizes.tiny,
        fontWeight = FontWeight.SemiBold,
    )
    val xsRegular = TextToken(
        fontSize = DefaultYDFontSizes.tiny,
    )
}

internal data class YDTypographyToken(
    private val compactFontSize: TextUnit,
    private val fontFamily: FontFamily,
    private val fontWeight: FontWeight,
    private val lineHeightRatio: Float
) {
    fun textStyle(isCompact: Boolean): TextStyle {
        val fontSize = fontSize(isCompact = isCompact)

        return TextStyle(
            fontSize = fontSize,
            fontWeight = fontWeight,
            fontFamily = fontFamily,
            letterSpacing = 0.sp,
            lineHeight = fontSize * lineHeightRatio,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None,
            )
        )
    }

    private fun fontSize(isCompact: Boolean) = if (isCompact) compactFontSize else (compactFontSize.value + 2).sp
}

@Suppress("FunctionName")
private fun HeadlineToken(
    compactFontSize: TextUnit,
) = YDTypographyToken(
    compactFontSize = compactFontSize,
    fontWeight = FontWeight.Medium,
    lineHeightRatio = 1.25f,
    fontFamily = defaultFontFamily,
)

@Suppress("FunctionName")
private fun TextToken(
    fontSize: TextUnit,
    fontWeight: FontWeight = FontWeight.Normal,
) = YDTypographyToken(
    compactFontSize = fontSize,
    fontWeight = fontWeight,
    lineHeightRatio = 1.5f,
    fontFamily = defaultFontFamily,
)

private val defaultFontFamily = FontFamily(
    Font(resId = R.font.jakarta_semibold, FontWeight.SemiBold),
    Font(resId = R.font.jakarta_medium, FontWeight.Medium),
    Font(resId = R.font.jakarta_regular, FontWeight.Normal),
    Font(resId = R.font.roboto_semibold, FontWeight.SemiBold),
    Font(resId = R.font.roboto_medium, FontWeight.Medium),
    Font(resId = R.font.roboto_regular, FontWeight.Normal)
)

