@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.navigationdrawer

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import de.yanosdev.annotation.YDRevisionIn

/**
 * Colors used by [YDNavigationDrawer] and [YDNavigationDrawerItem].
 * Create instances via [YDNavigationDrawerDefaults.navigationDrawerColors].
 *
 * @param containerColor Background color of the drawer panel.
 * @param disabledItemContentColor Icon and label color when a destination is disabled.
 * @param indicatorColor Highlight background behind the selected drawer item.
 * @param selectedItemContentColor Icon and label color of the active destination.
 * @param unselectedItemContentColor Icon and label color of inactive destinations.
 */
@Immutable
class YDNavigationDrawerColors internal constructor(
    val containerColor: Color,
    val disabledItemContentColor: Color,
    val indicatorColor: Color,
    val selectedItemContentColor: Color,
    val unselectedItemContentColor: Color,
) {
    internal fun itemContentColor(selected: Boolean, enabled: Boolean): Color = when {
        !enabled -> disabledItemContentColor
        selected -> selectedItemContentColor
        else -> unselectedItemContentColor
    }
}
