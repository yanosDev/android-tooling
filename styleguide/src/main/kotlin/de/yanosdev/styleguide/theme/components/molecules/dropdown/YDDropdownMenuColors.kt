@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.dropdown

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import de.yanosdev.annotation.YDRevisionIn

/**
 * Colors used by [YDDropdownMenu] and [YDDropdownMenuItem].
 *
 * Obtain instances via [YDDropdownMenuDefaults.menuColors].
 *
 * @param containerColor Background color of the menu surface.
 * @param contentColor Default content color for enabled menu items.
 * @param disabledContentColor Content color applied when a menu item is disabled.
 */
@Immutable
class YDDropdownMenuColors internal constructor(
    val containerColor: Color,
    val contentColor: Color,
    val disabledContentColor: Color,
)
