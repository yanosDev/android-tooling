@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.navigationbar

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import de.yanosdev.annotation.YDRevisionIn

/**
 * Colors used by [YDNavigationBar] and [YDNavigationBarItem].
 * Create instances via [YDNavigationBarDefaults.navigationBarColors].
 *
 * @param containerColor Background color of the navigation bar.
 * @param disabledItemContentColor Icon and label color when a destination is disabled.
 * @param indicatorColor Pill-shaped highlight behind the icon of the selected item.
 * @param selectedItemContentColor Icon and label color of the active destination.
 * @param unselectedItemContentColor Icon and label color of inactive destinations.
 */
@Immutable
class YDNavigationBarColors internal constructor(
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
