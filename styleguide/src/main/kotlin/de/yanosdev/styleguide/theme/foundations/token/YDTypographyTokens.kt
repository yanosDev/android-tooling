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

internal object YDTypographyTokens {
    val h1 = HeadlineToken(
        compactFontSize = 30.sp,
        expandedFontSize = 32.sp,
    )
    val h2 = HeadlineToken(
        compactFontSize = 24.sp,
        expandedFontSize = 26.sp,
    )
    val h3 = HeadlineToken(
        compactFontSize = 20.sp,
        expandedFontSize = 22.sp,
    )
    val h4 = HeadlineToken(
        compactFontSize = 18.sp,
        expandedFontSize = 20.sp,
    )
    val h5 = HeadlineToken(
        compactFontSize = 16.sp,
        expandedFontSize = 18.sp,
    )
    val lgMediumBold = TextToken(
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
    )
    val lgRegular = TextToken(
        fontSize = 16.sp,
    )
    val mdMediumBold = TextToken(
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
    )
    val mdRegular = TextToken(
        fontSize = 14.sp,
    )
    val smMediumBold = TextToken(
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
    )
    val smRegular = TextToken(
        fontSize = 12.sp,
    )
    val xsMediumBold = TextToken(
        fontSize = 10.sp,
        fontWeight = FontWeight.SemiBold,
    )
    val xsRegular = TextToken(
        fontSize = 10.sp,
    )
}

internal data class YDTypographyToken(
    private val compactFontSize: TextUnit,
    private val expandedFontSize: TextUnit,
    private val fontFamily: FontFamily,
    private val fontWeight: FontWeight,
    private val lineHeightRatio: Float
) {
    fun textStyle(isCompact: Boolean): TextStyle {
        val fontSize = fontSize(isCompact)

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

    private fun fontSize(isCompact: Boolean) = if (isCompact) compactFontSize else expandedFontSize
}

@Suppress("FunctionName")
private fun HeadlineToken(
    compactFontSize: TextUnit,
    expandedFontSize: TextUnit,
) = YDTypographyToken(
    compactFontSize = compactFontSize,
    expandedFontSize = expandedFontSize,
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
    expandedFontSize = fontSize,
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

