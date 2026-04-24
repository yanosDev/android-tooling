# Atoms

Smallest self-contained primitives. Import from `de.yanosdev.styleguide.theme.components.atoms.*`.

---

## YDText

Renders themed text. Uses `LocalYDTextStyle` and `LocalYDContentColor` by default. The `AnnotatedString` overload
supports clickable link annotations.

```kotlin
YDText(
    text = "Hello world",
    style = YDTheme.typography.mdRegular,
    maxLines = 2,
    overflow = TextOverflow.Ellipsis,
)
```

---

## YDSurface

Themed surface container that applies background color, shape, shadow, tonal elevation, and an optional border. A
second overload adds click + long-click handling with ripple.

```kotlin
// Static surface
YDSurface(
    shape = YDTheme.shapes.medium,
    shadowElevation = YDShadow.Low,
) {
    YDText(text = "Content")
}

// Clickable surface
YDSurface(
    onClick = { /* handle */ },
    shape = YDTheme.shapes.medium,
    enabled = true,
) {
    YDText(text = "Tap me")
}
```

---

## YDButton

Low-level button primitive. Prefer the molecule wrappers (`YDPrimaryButton`, `YDSecondaryButton`, `YDTextButton`) for
standard use. Use `YDButton` directly when you need custom colors via `YDButtonDefaults.buttonColors()`.

```kotlin
// Text convenience overload
YDButton(
    colors = YDButtonDefaults.buttonColors(),
    text = "Submit",
    onClick = { /* handle */ },
    enabled = true,
    loading = false,
)

// Custom content overload
YDButton(
    colors = YDButtonDefaults.buttonColors(),
    onClick = { /* handle */ },
) {
    YDIcon(imageVector = Icons.Rounded.Check, contentDescription = null)
    YDText(text = "Done")
}
```

---

## YDButtonDefaults

Factory for `YDButtonColors`. All molecule buttons delegate to this.

```kotlin
val colors = YDButtonDefaults.buttonColors(
    containerColor = YDTheme.colorScheme.primary,
    contentColor = YDTheme.colorScheme.onPrimary,
)
```

---

## YDIcon

Renders a tinted icon from an `ImageVector`, `Painter`, or `ImageBitmap`. Defaults to `LocalYDContentColor`.

```kotlin
YDIcon(
    imageVector = Icons.Rounded.Search,
    contentDescription = "Search",
)
```

---

## YDToggleableIcon

Icon that animates a strike-through line when `toggledOn = false`.

```kotlin
YDToggleableIcon(
    imageVector = Icons.Rounded.Notifications,
    contentDescription = "Notifications",
    toggledOn = isEnabled,
)
```

---

## YDDivider / YDVerticalDivider

Full-width horizontal (or full-height vertical) line using `colorScheme.line`.

```kotlin
YDDivider()

YDVerticalDivider()
```

Custom thickness:

```kotlin
YDDivider(thickness = 2.dp)
```

---

## YDBadgeIndicator

Circular badge with text. Single-character badges render as a circle; longer strings use pill padding.

```kotlin
YDBadgeIndicator(text = "3")

YDBadgeIndicator(text = "NEW")
```

`YDBetaBadge` is a zero-argument convenience wrapper that renders a "Beta" badge:

```kotlin
YDBetaBadge()
```

---

## YDDotIndicator

Small filled circle used as a notification dot on tabs or list items.

```kotlin
YDDotIndicator()

YDDotIndicator(
    color = YDTheme.colorScheme.onSurfaceSignal,
    size = YDTheme.spacings.small,
)
```

---

## YDCircularProgressIndicator

Indeterminate (no `progress` argument) or determinate (`progress: Float` in `0f..1f`) circular spinner.

```kotlin
// Indeterminate
YDCircularProgressIndicator()

// Determinate
YDCircularProgressIndicator(
    progress = 0.75f,
)
```

---

## YDLinearProgressIndicator

Horizontal progress bar. Always determinate; pass `progress` in `0f..1f`.

```kotlin
YDLinearProgressIndicator(
    progress = 0.4f,
)
```
