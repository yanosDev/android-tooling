# Molecules

Composed atoms that form a recognizable UI unit. Import from
`de.yanosdev.styleguide.theme.components.molecules.*`.

---

## YDCard / YDOutlinedCard

Surface-backed cards. `YDCard` uses the filled style (`surfaceContainerDefault` background). `YDOutlinedCard` adds a
visible `colorScheme.line` border on a `surface` background. Both have a static and a clickable overload.

```kotlin
// Static filled card
YDCard {
    Text("Hello")
}

// Clickable filled card
YDCard(onClick = { /* handle */ }) {
    Text("Tap me")
}

// Static outlined card
YDOutlinedCard {
    Text("Outlined")
}

// Clickable outlined card
YDOutlinedCard(onClick = { /* handle */ }) {
    Text("Tap me")
}
```

---

## YDSelectableCard

Filled card that animates between unselected and selected color tokens when `selected` changes. Caller owns the
selection state.

```kotlin
var selected by remember { mutableStateOf(false) }

YDSelectableCard(
    selected = selected,
    onClick = { selected = !selected },
) {
    Text(if (selected) "Selected" else "Tap to select")
}
```

---

## YDToggleableCard

Like `YDSelectableCard` but exposes `onToggleChange: (Boolean) -> Unit`, matching the toggle contract used by switches
and checkboxes. Useful when you want the new value delivered directly rather than the caller computing it.

```kotlin
var toggled by remember { mutableStateOf(false) }

YDToggleableCard(
    toggled = toggled,
    onToggleChange = { toggled = it },
) {
    Text(if (toggled) "On" else "Off")
}
```

---

## YDFilterChip / YDAssistChip / YDInputChip

Pill-shaped chips for filtering, quick actions, and representing dismissible user input. Colors are
created via `YDChipDefaults`; the `YDChipColors` constructor is internal.

```kotlin
// Filter chip — toggleable, animated check icon appears when selected
var selected by remember { mutableStateOf(false) }
YDFilterChip(
    selected = selected,
    text = "Technology",
    onSelectedChange = { selected = it },
)

// Assist chip — non-toggleable, triggers onClick, optional leading icon
YDAssistChip(
    text = "Add filter",
    onClick = { /* handle */ },
    leadingIcon = { YDIcon(imageVector = YDIcons.Filter, contentDescription = null) },
)

// Input chip — represents a user's selection, has a trailing × dismiss button
YDInputChip(
    text = "Kotlin",
    onDismiss = { /* remove this chip */ },
    leadingIcon = { YDIcon(imageVector = YDIcons.Code, contentDescription = null) },
    onClick = { /* edit the value */ },
)
```

All three accept `enabled = false` for the disabled state and an optional `colors` parameter.

---

## YDPrimaryButton

Filled primary-color button. The most prominent call-to-action.

```kotlin
YDPrimaryButton(
    text = "Confirm",
    onClick = { /* handle */ },
)

// Custom content
YDPrimaryButton(
    onClick = { /* handle */ },
    loading = true,
) {
    YDIcon(imageVector = YDIcons.Check, contentDescription = null)
    YDText(text = "Done")
}
```

---

## YDSecondaryButton

Outlined button with a transparent background. Use `opaqueBackground = true` on colored surfaces.

```kotlin
YDSecondaryButton(
    text = "Cancel",
    onClick = { /* handle */ },
)

YDSecondaryButton(
    text = "Cancel",
    onClick = { /* handle */ },
    opaqueBackground = true,
)
```

---

## YDTextButton

Underlined text-only button for inline actions.

```kotlin
YDTextButton(
    text = "Forgot password?",
    onClick = { /* handle */ },
)
```

`YDSlimTextButton` removes horizontal padding — use it in tight layouts:

```kotlin
YDSlimTextButton(
    text = "Learn more",
    onClick = { /* handle */ },
)
```

`YDTopAppBarTextButton` is a padding-free variant intended for top app bar action slots:

```kotlin
YDTopAppBarTextButton(
    text = "Save",
    onClick = { /* handle */ },
)
```

---

## YDIconButton

Minimum-touch-target icon button with optional custom colors.

```kotlin
YDIconButton(onClick = { /* handle */ }) {
    YDIcon(imageVector = YDIcons.Search, contentDescription = "Search")
}
```

`YDIconToggleButton` adds a checked/unchecked state:

```kotlin
YDIconToggleButton(
    checked = isFavorite,
    onCheckedChange = { isFavorite = it },
) {
    YDIcon(imageVector = YDIcons.Favorite, contentDescription = "Favorite")
}
```

---

## YDTab

A single tab with optional leading, text, and trailing slots. Animates content color between
`selectedContentColor` and `unselectedContentColor`.

```kotlin
YDTab(
    selected = selectedIndex == 0,
    onClick = { selectedIndex = 0 },
    text = { YDText(text = "Feed") },
)

// With badge trailing
YDTab(
    selected = selectedIndex == 1,
    onClick = { selectedIndex = 1 },
    text = { YDText(text = "Inbox") },
    trailing = {
        YDBadgeIndicator(
            text = "3",
            modifier = Modifier.padding(start = YDTheme.spacings.small),
        )
    },
)
```

---

## YDTabRow

Fixed-width tab row that distributes tabs evenly and shows an animated indicator underline.

```kotlin
YDTabRow(selectedTabIndex = selectedIndex) {
    tabs.forEachIndexed { index, label ->
        YDTab(
            selected = index == selectedIndex,
            onClick = { selectedIndex = index },
            text = { YDText(text = label) },
        )
    }
}
```

`YDScrollableTabRow` scrolls horizontally when there are more tabs than fit the screen:

```kotlin
YDScrollableTabRow(selectedTabIndex = selectedIndex) {
    tabs.forEachIndexed { index, label ->
        YDTab(
            selected = index == selectedIndex,
            onClick = { selectedIndex = index },
            text = { YDText(text = label) },
        )
    }
}
```

---

## YDTabWithBadge

Convenience tab that shows a `YDDotIndicator` as a leading badge when `hasBadge = true`.

```kotlin
YDTabWithBadge(
    selected = selectedIndex == 0,
    text = "Notifications",
    onClick = { selectedIndex = 0 },
    hasBadge = hasUnread,
)
```

---

## YDTopAppBar

Standard single-row top app bar with navigation icon, centered (or start-aligned) title, and action slots.

```kotlin
YDTopAppBar(
    navigationIcon = { YDNavigationUpIcon(navUp = navBack) },
    title = "Detail",
    actions = {
        YDIconButton(onClick = onShare) {
            YDIcon(imageVector = YDIcons.Share, contentDescription = "Share")
        }
    },
)
```

Composable title overload for custom title content:

```kotlin
YDTopAppBar(
    title = { YDText(text = screenTitle) },
    navigationIcon = { YDNavigationUpIcon(navUp = navBack) },
)
```

---

## YDScaffold

Screen-level layout container that slots a `topBar`, `bottomBar`, `floatingActionButton`, and `snackbarHost`.
The `content` lambda receives `PaddingValues` that account for all slotted bars and window insets.

```kotlin
YDScaffold(
    topBar = {
        YDTopAppBar(
            navigationIcon = { YDNavigationUpIcon(navUp = navBack) },
            title = "Settings",
        )
    },
) { contentPadding ->
    LazyColumn(contentPadding = contentPadding) {
        // items
    }
}
```

---

## YDAlertDialog

Modal alert dialog. Two overloads: composable-slot API and convenience string API.

**Slot-based** — full control over every content area:

```kotlin
YDAlertDialog(
    onDismissRequest = { showDialog = false },
    title = { YDText(text = "Delete item?") },
    text = { YDText(text = "This action cannot be undone.") },
    confirmButton = { YDPrimaryButton(text = "Delete", onClick = { /* delete */ }) },
    dismissButton = { YDTextButton(text = "Cancel", onClick = { showDialog = false }) },
)
```

**String-based** — confirm button is always shown; dismiss button is shown when `dismissText` is non-null
and defaults to calling `onDismissRequest`:

```kotlin
YDAlertDialog(
    confirmText = "Delete",
    onConfirmClick = { /* delete */ },
    onDismissRequest = { showDialog = false },
    title = "Delete item?",
    text = "This action cannot be undone.",
    dismissText = "Cancel",
)
```

Custom icon:

```kotlin
YDAlertDialog(
    onDismissRequest = { showDialog = false },
    icon = { YDIcon(imageVector = YDIcons.Warning, contentDescription = null) },
    title = { YDText(text = "Warning") },
    text = { YDText(text = "Something went wrong.") },
    confirmButton = { YDPrimaryButton(text = "OK", onClick = { showDialog = false }) },
)
```

---

## YDTimePickerDialog

Modal scroll-wheel time picker dialog. Maintains internal state; confirmed value is delivered via
`onConfirm`. The dialog does **not** auto-dismiss — call `onDismissRequest` inside `onConfirm`.

```kotlin
YDTimePickerDialog(
    onConfirm = { time ->
        selectedTime = time   // YDPickerTime(hour, minute, isAm)
        showDialog = false
    },
    onDismissRequest = { showDialog = false },
    title = "Select time",
    dismissText = "Cancel",
)
```

24-hour format:

```kotlin
YDTimePickerDialog(
    onConfirm = { time -> /* time.hour is 0–23 */ },
    onDismissRequest = { showDialog = false },
    initialValue = YDPickerTime(hour = 14, minute = 30),
    is24HourFormat = true,
)
```

---

## YDDatePickerDialog

Modal scroll-wheel date picker dialog (day / month / year). Day values are automatically clamped
when the month or year changes (e.g. scrolling from Jan 31 to Feb clamps to Feb 28/29).

```kotlin
YDDatePickerDialog(
    onConfirm = { date ->
        selectedDate = date   // YDPickerDate(day, month, year)
        showDialog = false
    },
    onDismissRequest = { showDialog = false },
    title = "Select date",
    dismissText = "Cancel",
    yearRange = 2020..2030,
)
```

---

## YDMonthYearPickerDialog

Modal scroll-wheel month and year picker. Useful for expiry dates, filter ranges, and similar
month-granularity selections.

```kotlin
YDMonthYearPickerDialog(
    onConfirm = { yearMonth ->
        expiry = yearMonth   // YDPickerYearMonth(month, year)
        showDialog = false
    },
    onDismissRequest = { showDialog = false },
    title = "Expiry date",
    dismissText = "Cancel",
    yearRange = 2024..2040,
)
```

---

## YDFab / YDSmallFab

Circular floating action buttons. `YDFab` is 56 × 56 dp (primary action); `YDSmallFab` is 40 × 40 dp
(secondary/contextual action). The `content` slot is centered — typically a single `YDIcon`.

```kotlin
YDFab(onClick = { /* handle */ }) {
    YDIcon(imageVector = YDIcons.Add, contentDescription = "Add")
}

YDSmallFab(onClick = { /* handle */ }) {
    YDIcon(imageVector = YDIcons.Edit, contentDescription = "Edit")
}
```

---

## YDExtendedFab

Extended FAB with a label and optional leading icon. When `expanded = false` the label animates
out horizontally, leaving only the icon — ideal for collapsing while the user scrolls.

```kotlin
var expanded by remember { mutableStateOf(true) }

YDExtendedFab(
    text = "New entry",
    onClick = { /* handle */ },
    expanded = expanded,
    icon = { YDIcon(imageVector = YDIcons.Add, contentDescription = null) },
)
```

---

## YDDropdownMenu / YDDropdownMenuItem

Context menu anchored to a `Box` trigger. Wrap the anchor and menu in a `Box`; the menu anchors
to the bottom-start corner of that `Box`.

```kotlin
var expanded by remember { mutableStateOf(false) }

Box {
    YDIconButton(onClick = { expanded = true }) {
        YDIcon(imageVector = YDIcons.Menu, contentDescription = "More")
    }
    YDDropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
    ) {
        YDDropdownMenuItem(text = "Edit", onClick = { expanded = false })
        YDDropdownMenuItem(
            text = "Share",
            onClick = { expanded = false },
            leadingIcon = { YDIcon(imageVector = YDIcons.Share, contentDescription = null) },
        )
        YDDropdownMenuDivider()
        YDDropdownMenuItem(
            text = "Delete",
            onClick = { expanded = false },
            leadingIcon = { YDIcon(imageVector = YDIcons.Trash, contentDescription = null) },
        )
    }
}
```

---

## YDSearchBar

Pill-shaped search input with a leading search icon and auto-appearing trailing clear button.
Caller controls `query` state. `onSearch` fires when the user presses the IME search action.

```kotlin
var query by remember { mutableStateOf("") }

YDSearchBar(
    query = query,
    onQueryChange = { query = it },
    placeholder = "Search watches…",
    onSearch = { performSearch(it) },
)
```

---

## YDSnackbar / YDSnackbarHost / YDSnackbarHostState

`YDSnackbarHostState` queues messages and serialises display. Call `showSnackbar` from a coroutine;
it suspends until the snackbar is dismissed. Place `YDSnackbarHost` in the `snackbarHost` slot of
`YDScaffold` (or `YDDefaultScreen`).

```kotlin
val snackbarState = remember { YDSnackbarHostState() }
val scope = rememberCoroutineScope()

YDScaffold(
    snackbarHost = { YDSnackbarHost(hostState = snackbarState) },
) { contentPadding ->
    // screen content
}

// Trigger a snackbar:
scope.launch { snackbarState.showSnackbar(message = "Saved") }

// With action:
scope.launch {
    val result = snackbarState.showSnackbar(
        message = "Item deleted",
        actionLabel = "Undo",
    )
    if (result == YDSnackbarResult.ActionPerformed) undoDelete()
}
```

---

## YDNavigationBar / YDNavigationBarItem

Bottom navigation bar for compact-width screens. One item should be selected at a time; caller
owns state. Bottom system-bar insets are consumed automatically.

```kotlin
var selected by remember { mutableIntStateOf(0) }

YDNavigationBar {
    YDNavigationBarItem(
        selected = selected == 0,
        onClick = { selected = 0 },
        label = { YDText(text = "Home", style = typography.xsRegular) },
    ) {
        YDIcon(imageVector = YDIcons.Home, contentDescription = "Home")
    }
    YDNavigationBarItem(
        selected = selected == 1,
        onClick = { selected = 1 },
        label = { YDText(text = "Search", style = typography.xsRegular) },
    ) {
        YDIcon(imageVector = YDIcons.Search, contentDescription = "Search")
    }
}
```

---

## YDNavigationRail / YDNavigationRailItem

Vertical navigation rail for medium-width screens. Mirrors `YDNavigationBar` semantics but runs
along the left edge. Accepts an optional `header` slot above the items.

```kotlin
YDNavigationRail(header = { YDIcon(imageVector = YDIcons.Bolt, contentDescription = null) }) {
    YDNavigationRailItem(
        selected = selected == 0,
        onClick = { selected = 0 },
        label = { YDText(text = "Home", style = typography.xsRegular) },
    ) {
        YDIcon(imageVector = YDIcons.Home, contentDescription = "Home")
    }
}
```

---

## YDNavigationDrawer / YDNavigationDrawerItem

Persistent navigation drawer for expanded-width screens (tablets/desktops). Accepts an optional
`header` slot. Items are rendered as rows with icon + label.

```kotlin
YDNavigationDrawer(header = { AppLogo() }) {
    YDNavigationDrawerItem(
        label = "Home",
        selected = selected == 0,
        onClick = { selected = 0 },
        icon = { YDIcon(imageVector = YDIcons.Home, contentDescription = null) },
    )
    YDNavigationDrawerItem(
        label = "Settings",
        selected = selected == 1,
        onClick = { selected = 1 },
        icon = { YDIcon(imageVector = YDIcons.Settings, contentDescription = null) },
    )
}
```

---

## YDBottomAppBar

Bottom app bar for screen-level actions. The `content` slot holds icon buttons at the start;
the optional `floatingActionButton` slot places a FAB at the end. Not for navigation — use
`YDNavigationBar` for navigation destinations.

```kotlin
YDBottomAppBar(
    floatingActionButton = {
        YDFab(onClick = { /* primary action */ }) {
            YDIcon(imageVector = YDIcons.Add, contentDescription = "Add")
        }
    },
) {
    YDIconButton(onClick = { /* menu */ }) {
        YDIcon(imageVector = YDIcons.Menu, contentDescription = "Menu")
    }
    YDIconButton(onClick = { /* search */ }) {
        YDIcon(imageVector = YDIcons.Search, contentDescription = "Search")
    }
}
```

---

## YDBottomSheet / YDBottomSheetDialog

Modal bottom sheet with predictive-back support, scroll integration, and anchor-draggable gesture
handling. Use `rememberYDSheetState()` to control expand/collapse state. `YDBottomSheetDialog`
is the full-screen dialog host; `YDBottomSheet` renders the sheet surface inside it.

```kotlin
val sheetState = rememberYDSheetState()
val scope = rememberCoroutineScope()

if (showSheet) {
    YDBottomSheetDialog(
        sheetState = sheetState,
        onDismissRequest = { showSheet = false },
    ) {
        YDBottomSheet(sheetState = sheetState) {
            Column(modifier = Modifier.padding(all = YDTheme.spacings.large)) {
                YDText(text = "Sheet content")
                YDPrimaryButton(
                    text = "Close",
                    onClick = { scope.launch { sheetState.hide() } },
                )
            }
        }
    }
}
```
