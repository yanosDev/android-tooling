@file:YDRevisionIn(implementedAt = "2026-04-19", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.home.model

import de.yanosdev.annotation.YDRevisionIn

internal data class HomeScreenData(
    val items: List<StyleGuideItems> = listOf(
        StyleGuideItems.SubAtoms.Colors,
        StyleGuideItems.SubAtoms.Typographies,
        StyleGuideItems.Atoms.Text,
        StyleGuideItems.Atoms.Icon,
        StyleGuideItems.Atoms.IconButton,
        StyleGuideItems.Atoms.Selection,
        StyleGuideItems.Atoms.Surface,
        StyleGuideItems.Atoms.Scaffold,
        StyleGuideItems.Molecules.Button,
        StyleGuideItems.Molecules.Card,
        StyleGuideItems.Molecules.Chip,
        StyleGuideItems.Molecules.Dialog,
        StyleGuideItems.Molecules.Picker,
    )
)