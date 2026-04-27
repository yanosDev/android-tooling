# Styleguide Component Documentation

The `styleguide` module is a Compose design system for Android built on the **Atomic Design** pattern.
Components are organized into three tiers:

- **Atoms** — smallest, self-contained primitives (text, icon, button shell, divider, indicators,
  progress, selection controls, slider).
- **Molecules** — compositions of atoms that form a recognizable UI unit (buttons, cards, chips,
  dialogs, pickers, tabs, top/bottom app bar, scaffold, search bar, snackbar, dropdown, FAB,
  navigation bar/rail/drawer, bottom sheet).
- **Organisms** — full-screen patterns that wire architecture together (default screen layout,
  ViewModel contract, UIState machine).

All components live under the package `de.yanosdev.styleguide.theme.*` and must be called with
named arguments at all call sites inside `de.yanosdev.*` code.

---

## Contents

- [Atoms](atoms.md)
- [Molecules](molecules.md)
- [Organisms](organisms.md)

---

## Quick reference

### Sub-atoms (design tokens)

| Token object         | Accessed via            | Description                               |
|----------------------|-------------------------|-------------------------------------------|
| `YDColorTokens`      | `YDTheme.colorScheme.*` | Raw color palette                         |
| `YDTypographyTokens` | `YDTheme.typography.*`  | Type scale                                |
| `YDShadowTokens`     | `YDTheme.shadows.*`     | Shadow elevation values                   |
| `YDColors`           | `YDTheme.colorScheme`   | Semantic color wrapper (CompositionLocal) |
| `YDTypography`       | `YDTheme.typography`    | Semantic typography wrapper               |
| `YDShapes`           | `YDTheme.shapes.*`      | Corner radius tokens                      |
| `YDSpacings`         | `YDTheme.spacings.*`    | Spacing scale                             |

### Atoms

| Component                                                   | Package           |
|-------------------------------------------------------------|-------------------|
| `YDText`                                                    | `atoms.text`      |
| `YDSurface`                                                 | `atoms`           |
| `YDButton` / `YDButtonDefaults`                             | `atoms.button`    |
| `YDIcon` / `YDIcons`                                        | `atoms.icon`      |
| `YDDivider` / `YDVerticalDivider`                           | `atoms.divider`   |
| `YDCheckbox` / `YDRadioButton` / `YDSwitch`                 | `atoms.selection` |
| `YDCircularProgressIndicator` / `YDLinearProgressIndicator` | `atoms.progress`  |
| `YDBadgeIndicator` / `YDDotIndicator`                       | `atoms.indicator` |
| `YDSlider`                                                  | `atoms.slider`    |

### Molecules

| Component                                                               | Package                      |
|-------------------------------------------------------------------------|------------------------------|
| `YDPrimaryButton` / `YDSecondaryButton` / `YDTextButton`                | `molecules.button`           |
| `YDIconButton` / `YDIconToggleButton`                                   | `molecules.button.icon`      |
| `YDCard` / `YDOutlinedCard` / `YDSelectableCard` / `YDToggleableCard`   | `molecules.card`             |
| `YDFilterChip` / `YDAssistChip` / `YDInputChip`                         | `molecules.chip`             |
| `YDAlertDialog`                                                         | `molecules.dialog`           |
| `YDTimePickerDialog` / `YDDatePickerDialog` / `YDMonthYearPickerDialog` | `molecules.picker`           |
| `YDTab` / `YDTabRow` / `YDScrollableTabRow` / `YDTabWithBadge`          | `molecules.tabs`             |
| `YDTopAppBar`                                                           | `molecules.topbar`           |
| `YDScaffold`                                                            | `molecules.scaffold`         |
| `YDSearchBar`                                                           | `molecules.searchbar`        |
| `YDSnackbar` / `YDSnackbarHost` / `YDSnackbarHostState`                 | `molecules.snackbar`         |
| `YDDropdownMenu` / `YDDropdownMenuItem`                                 | `molecules.dropdown`         |
| `YDFab` / `YDSmallFab` / `YDExtendedFab`                                | `molecules.fab`              |
| `YDNavigationBar` / `YDNavigationBarItem`                               | `molecules.navigationbar`    |
| `YDNavigationRail` / `YDNavigationRailItem`                             | `molecules.navigationrail`   |
| `YDNavigationDrawer` / `YDNavigationDrawerItem`                         | `molecules.navigationdrawer` |
| `YDBottomAppBar`                                                        | `molecules.bottomappbar`     |
| `YDBottomSheet` / `YDBottomSheetDialog`                                 | `molecules.bottomsheet`      |

### Organisms

| Component                                      | Package                  |
|------------------------------------------------|--------------------------|
| `YDNavigationUpIcon` / `YDNavigationCloseIcon` | `organisms.navigation`   |
| `YDDefaultScreen`                              | `organisms.screen`       |
| `YDUIContent` / `YDUIContentScope`             | `organisms.screen`       |
| `YDViewModel` / `YDViewModelImpl`              | `organisms.screen`       |
| `UIState`                                      | `organisms.screen.model` |
