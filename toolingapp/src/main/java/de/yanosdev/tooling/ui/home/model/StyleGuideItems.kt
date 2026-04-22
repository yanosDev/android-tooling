@file:YDRevisionIn(implementedAt = "2026-04-19", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.home.model

import de.yanosdev.annotation.YDRevisionIn

sealed interface StyleGuideItems {

    sealed interface SubAtoms : StyleGuideItems {
        data object Colors : SubAtoms
        data object Typographies : SubAtoms
        data object Shadows : SubAtoms
    }

    sealed interface Atoms : StyleGuideItems {
        data object Text : Atoms
        data object Icon : Atoms
        data object IconButton : Molecules
        data object Surface : Atoms
        data object Scaffold : Atoms
    }

    sealed interface Molecules : StyleGuideItems

}
