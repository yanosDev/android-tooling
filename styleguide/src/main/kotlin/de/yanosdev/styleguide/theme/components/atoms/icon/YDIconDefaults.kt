@file:YDRevisionIn(implementedAt = "2026-04-19", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.atoms.icon

import androidx.compose.runtime.Composable
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.themes.YDTheme.sizes

internal object YDIconDefaults {
    val smallSize @Composable get() = sizes.extraTiny
    val mediumSize @Composable get() = sizes.tiny
    val largeSize @Composable get() = sizes.small
}