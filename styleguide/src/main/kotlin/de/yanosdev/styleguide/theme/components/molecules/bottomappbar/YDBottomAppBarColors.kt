@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.bottomappbar

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import de.yanosdev.annotation.YDRevisionIn

/**
 * Colors used by [YDBottomAppBar].
 * Create instances via [YDBottomAppBarDefaults.bottomAppBarColors].
 *
 * @param containerColor Background color of the bar.
 * @param contentColor Default tint for icons and other content inside the bar.
 */
@Immutable
class YDBottomAppBarColors internal constructor(
    val containerColor: Color,
    val contentColor: Color,
)
