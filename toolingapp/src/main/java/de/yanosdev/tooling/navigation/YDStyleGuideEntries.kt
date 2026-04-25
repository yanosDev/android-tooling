@file:YDRevisionIn(implementedAt = "2026-04-17", revisionAfterInDays = 365)

package de.yanosdev.tooling.navigation

import androidx.navigation3.runtime.EntryProviderScope
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.tooling.ui.button.ButtonScreen
import de.yanosdev.tooling.ui.button.ButtonViewModelImpl
import de.yanosdev.tooling.ui.card.CardScreen
import de.yanosdev.tooling.ui.card.CardViewModelImpl
import de.yanosdev.tooling.ui.colors.ColorsScreen
import de.yanosdev.tooling.ui.colors.ColorsViewModelImpl
import de.yanosdev.tooling.ui.dialog.DialogScreen
import de.yanosdev.tooling.ui.dialog.DialogViewModelImpl
import de.yanosdev.tooling.ui.home.HomeScreen
import de.yanosdev.tooling.ui.home.viewmodel.HomeViewModelImpl
import de.yanosdev.tooling.ui.icon.IconScreen
import de.yanosdev.tooling.ui.icon.IconViewModelImpl
import de.yanosdev.tooling.ui.picker.PickerScreen
import de.yanosdev.tooling.ui.picker.PickerViewModelImpl
import de.yanosdev.tooling.ui.typographies.TypographiesScreen
import de.yanosdev.tooling.ui.typographies.TypographiesViewModelImpl

internal fun EntryProviderScope<YDStyleGuideNavKey>.ydStyleGuideNavEntries(navigator: YDStyleGuideNavigator) {
    entry<YDStyleGuideNavKey.Home> {
        HomeScreen(
            viewModel = HomeViewModelImpl(),
            navToItem = navigator::navigate,
            navBack = navigator.navigationIcon
        )
    }
    entry<YDStyleGuideNavKey.Colors> {
        ColorsScreen(
            navBack = navigator.navigationIcon,
            viewModel = ColorsViewModelImpl()
        )
    }

    entry<YDStyleGuideNavKey.Typographies> {
        TypographiesScreen(
            navBack = navigator.navigationIcon,
            viewModel = TypographiesViewModelImpl()
        )
    }

    entry<YDStyleGuideNavKey.Button> {
        ButtonScreen(
            navBack = navigator.navigationIcon,
            viewModel = ButtonViewModelImpl()
        )
    }

    entry<YDStyleGuideNavKey.Icon> {
        IconScreen(
            navBack = navigator.navigationIcon,
            viewModel = IconViewModelImpl()
        )
    }

    entry<YDStyleGuideNavKey.Dialog> {
        DialogScreen(
            navBack = navigator.navigationIcon,
            viewModel = DialogViewModelImpl()
        )
    }

    entry<YDStyleGuideNavKey.Picker> {
        PickerScreen(
            navBack = navigator.navigationIcon,
            viewModel = PickerViewModelImpl()
        )
    }

    entry<YDStyleGuideNavKey.Card> {
        CardScreen(
            navBack = navigator.navigationIcon,
            viewModel = CardViewModelImpl()
        )
    }

    /*

            entry<YDStyleGuideNavKey.Shadows> {
                ShadowsScreen(
                    navBack = navigator.navigationIcon
                )
            }


            entry<YDStyleGuideNavKey.TextButtons> {
                TextButtonsScreen(
                    navBack = navigator.navigationIcon
                )
            }

            entry<YDStyleGuideNavKey.Text> {
                TextScreen(
                    navBack = navigator.navigationIcon
                )
            }

            entry<YDStyleGuideNavKey.Icon> {
                IconScreen(
                    navBack = navigator.navigationIcon
                )
            }

            entry<YDStyleGuideNavKey.IconButton> {
                IconButtonScreen(
                    navBack = navigator.navigationIcon
                )
            }

            entry<YDStyleGuideNavKey.Surface> {
                SurfaceScreen(
                    navBack = navigator.navigationIcon
                )
            }

            entry<YDStyleGuideNavKey.Scaffold> {
                ScaffoldScreen(
                    navBack = navigator.navigationIcon
                )
            }*/
}