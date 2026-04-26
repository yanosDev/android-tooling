@file:YDRevisionIn(implementedAt = "2026-04-19", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.home.model

import de.yanosdev.annotation.YDRevisionIn

sealed interface StyleGuideItems {

    sealed interface SubAtoms : StyleGuideItems {
        data object Colors : SubAtoms
        data object Typographies : SubAtoms
    }

    sealed interface Atoms : StyleGuideItems {
        data object Icon : Atoms
        data object Selection : Atoms
        data object Slider : Atoms
    }

    sealed interface Molecules : StyleGuideItems {
        data object Button : Molecules
        data object Card : Molecules
        data object Chip : Molecules
        data object Dialog : Molecules
        data object Dropdown : Molecules
        data object Fab : Molecules
        data object Picker : Molecules
        data object SearchBar : Molecules
        data object Snackbar : Molecules
    }

}
