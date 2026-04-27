@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.atoms.selection

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import de.yanosdev.annotation.YDRevisionIn

/**
 * Colors shared by [YDCheckbox], [YDRadioButton], and [YDSwitch].
 *
 * | Property              | Checkbox        | RadioButton  | Switch              |
 * |-----------------------|-----------------|--------------|---------------------|
 * | activeColor           | fill + border   | dot + ring   | track when on       |
 * | activeContentColor    | checkmark       | —            | thumb when on       |
 * | inactiveColor         | border          | ring         | track when off      |
 * | inactiveContentColor  | —               | —            | thumb when off      |
 *
 * Instances are created via [YDSelectionDefaults]; the constructor is not part of the public API.
 */
@Immutable
class YDSelectionColors internal constructor(
    val activeColor: Color,
    val activeContentColor: Color,
    val disabledActiveColor: Color,
    val disabledActiveContentColor: Color,
    val disabledInactiveColor: Color,
    val disabledInactiveContentColor: Color,
    val inactiveColor: Color,
    val inactiveContentColor: Color,
) {
    internal fun activeColor(enabled: Boolean) = if (enabled) activeColor else disabledActiveColor
    internal fun activeContentColor(enabled: Boolean) = if (enabled) activeContentColor else disabledActiveContentColor
    internal fun inactiveColor(enabled: Boolean) = if (enabled) inactiveColor else disabledInactiveColor
    internal fun inactiveContentColor(enabled: Boolean) =
        if (enabled) inactiveContentColor else disabledInactiveContentColor

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is YDSelectionColors) return false
        if (activeColor != other.activeColor) return false
        if (activeContentColor != other.activeContentColor) return false
        if (disabledActiveColor != other.disabledActiveColor) return false
        if (disabledActiveContentColor != other.disabledActiveContentColor) return false
        if (disabledInactiveColor != other.disabledInactiveColor) return false
        if (disabledInactiveContentColor != other.disabledInactiveContentColor) return false
        if (inactiveColor != other.inactiveColor) return false
        if (inactiveContentColor != other.inactiveContentColor) return false
        return true
    }

    override fun hashCode(): Int {
        var result = activeColor.hashCode()
        result = 31 * result + activeContentColor.hashCode()
        result = 31 * result + disabledActiveColor.hashCode()
        result = 31 * result + disabledActiveContentColor.hashCode()
        result = 31 * result + disabledInactiveColor.hashCode()
        result = 31 * result + disabledInactiveContentColor.hashCode()
        result = 31 * result + inactiveColor.hashCode()
        result = 31 * result + inactiveContentColor.hashCode()
        return result
    }
}
