@file:OptIn(ExperimentalLayoutApi::class)

@file:YDRevisionIn(implementedAt = "2026-04-18", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.atoms

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.MutableWindowInsets
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import de.yanosdev.annotation.YDRevisionIn

/**
 * Derived from Material3 Scaffold
 */
@Composable
fun YDScaffold(
    modifier: Modifier = Modifier,
    applyImePadding: Boolean = true,
    contentWindowInsets: WindowInsets = YDScaffoldDefaults.contentWindowInsets,
    content: @Composable (PaddingValues) -> Unit
) {
    if (applyImePadding) Modifier.imePadding() else Modifier
    remember(contentWindowInsets) {
        MutableWindowInsets(contentWindowInsets)
    }
    YDSurface(
        modifier = modifier,
    ) {
        content(PaddingValues())
    }
}

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