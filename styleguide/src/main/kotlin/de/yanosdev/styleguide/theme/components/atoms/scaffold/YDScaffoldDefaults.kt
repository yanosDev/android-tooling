@file:YDRevisionIn(implementedAt = "2026-04-19", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.atoms.scaffold

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import de.yanosdev.annotation.YDRevisionIn

internal object YDScaffoldDefaults {
    /**
     * Default scaffold content window insets.
     *
     * Prevents content from overlapping any system bars.
     */
    val contentWindowInsets: WindowInsets
        @Composable
        get() = WindowInsets.systemBars

    /**
     * Default insets for a custom bottom bar in a scaffold.
     */
    val bottomBarInsets: WindowInsets
        @Composable
        get() = WindowInsets.systemBars.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom)
}