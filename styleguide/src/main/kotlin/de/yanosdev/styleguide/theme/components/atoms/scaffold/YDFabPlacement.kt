@file:YDRevisionIn(implementedAt = "2026-04-19", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.atoms.scaffold

import androidx.compose.runtime.Immutable
import de.yanosdev.annotation.YDRevisionIn

@Immutable
internal data class YDFabPlacement(
    val height: Int,
    val left: Int,
    val width: Int
)