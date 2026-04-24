@file:YDRevisionIn(implementedAt = "2026-04-24", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.dialog

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import de.yanosdev.annotation.YDRevisionIn

@Immutable
class YDAlertDialogColors internal constructor(
    val containerColor: Color,
    val iconContentColor: Color,
    val textContentColor: Color,
    val titleContentColor: Color,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is YDAlertDialogColors) return false
        if (containerColor != other.containerColor) return false
        if (iconContentColor != other.iconContentColor) return false
        if (textContentColor != other.textContentColor) return false
        if (titleContentColor != other.titleContentColor) return false
        return true
    }

    override fun hashCode(): Int {
        var result = containerColor.hashCode()
        result = 31 * result + iconContentColor.hashCode()
        result = 31 * result + textContentColor.hashCode()
        result = 31 * result + titleContentColor.hashCode()
        return result
    }
}
