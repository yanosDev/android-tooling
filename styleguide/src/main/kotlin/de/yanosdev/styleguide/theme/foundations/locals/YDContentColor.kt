@file:YDRevisionIn(implementedAt = "2026-04-16", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.foundations.locals

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.BuildConfig

val LocalYDContentColor = compositionLocalOf {
    if (BuildConfig.DEBUG) Color.Magenta else Color.Black
}