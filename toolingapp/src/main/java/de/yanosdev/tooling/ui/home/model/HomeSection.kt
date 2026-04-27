@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.home.model

import de.yanosdev.annotation.YDRevisionIn

internal enum class HomeSection(val label: String) {
    SubAtoms(label = "Sub-Atoms"),
    Atoms(label = "Atoms"),
    Molecules(label = "Molecules"),
}
