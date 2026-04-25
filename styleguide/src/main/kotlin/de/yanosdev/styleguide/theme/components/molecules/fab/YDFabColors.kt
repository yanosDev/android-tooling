@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.fab

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import de.yanosdev.annotation.YDRevisionIn

/**
 * Colors used by FAB components. Instances are created via [YDFabDefaults]; the constructor
 * is not part of the public API.
 */
@Immutable
class YDFabColors internal constructor(
    val containerColor: Color,
    val contentColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color,
) {
    internal fun containerColor(enabled: Boolean) = if (enabled) containerColor else disabledContainerColor
    internal fun contentColor(enabled: Boolean) = if (enabled) contentColor else disabledContentColor

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is YDFabColors) return false
        if (containerColor != other.containerColor) return false
        if (contentColor != other.contentColor) return false
        if (disabledContainerColor != other.disabledContainerColor) return false
        if (disabledContentColor != other.disabledContentColor) return false
        return true
    }

    override fun hashCode(): Int {
        var result = containerColor.hashCode()
        result = 31 * result + contentColor.hashCode()
        result = 31 * result + disabledContainerColor.hashCode()
        result = 31 * result + disabledContentColor.hashCode()
        return result
    }
}
