@file:YDRevisionIn(implementedAt = "2026-04-16", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.foundations.token

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.R

internal object YDFontTokens {
    val jakartaSemibold = Font(resId = R.font.jakarta_semibold, FontWeight.SemiBold)
    val jakartaMedium = Font(resId = R.font.jakarta_medium, FontWeight.Medium)
    val jakartaRegular = Font(resId = R.font.jakarta_regular, FontWeight.Normal)
    val robotoSemibold = Font(resId = R.font.roboto_semibold, FontWeight.SemiBold)
    val robotoMedium = Font(resId = R.font.roboto_medium, FontWeight.Medium)
    val robotoRegular = Font(resId = R.font.roboto_regular, FontWeight.Normal)
}