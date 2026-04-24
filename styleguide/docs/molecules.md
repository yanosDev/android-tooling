# Molecules

Composed atoms that form a recognizable UI unit. Import from
`de.yanosdev.styleguide.theme.components.molecules.*`.

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
    YDIcon(imageVector = Icons.Rounded.Check, contentDescription = null)
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
    YDIcon(imageVector = Icons.Rounded.Search, contentDescription = "Search")
}
```

`YDIconToggleButton` adds a checked/unchecked state:

```kotlin
YDIconToggleButton(
    checked = isFavorite,
    onCheckedChange = { isFavorite = it },
) {
    YDIcon(imageVector = Icons.Rounded.Favorite, contentDescription = "Favorite")
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
            YDIcon(imageVector = Icons.Rounded.Share, contentDescription = "Share")
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
