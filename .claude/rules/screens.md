---
paths:
  - "**/feature/**/*Screen.kt"
  - "**/feature/**/*ViewModel.kt"
  - "**/feature/**/*TrackingDefinitions.kt"
  - "**/feature/**/model/**/*.kt"
  - "**/feature/**/ui/**/*.kt"
---

# Screen pattern

When working on a screen — creating, modifying, or refactoring — follow the three-tier composable
structure used throughout `feature/*`. Canonical reference: `WatchScannerDetailsScreen.kt`.

## Package layout

Each screen's package splits into two sub-packages:

- **`model/`** — non-composable supporting types: `${Name}Action`, `${Name}State`,
  `${Name}ViewModelParameters`, plus any feature-local domain types (enums, small data classes)
  used by the state or actions. ViewModel parameter holders (typically a `data class` passed into
  the ViewModel constructor for Koin-injected runtime args) **must** live here, not next to the
  ViewModel.
- **`ui/`** — every feature composable that lives in its own file: the `Content` composable, any
  `Section` / sub-component composables, and other screen-local widgets. The `${Name}Screen.kt`
  file itself stays at the package root (it owns the three-tier `Screen` / private `Screen` /
  private `Content` composables, and `Content` is the one that delegates into `ui/` sub-components).

  Names in `ui/` must carry the screen/feature prefix. A composable called `EmailBanner` reads as
  a shared, reusable component — anyone could pull it in. `ContactSellerEmailBanner` is
  unambiguous: screen-local, not for reuse outside this feature. If a component genuinely is
  reusable, it does not belong in this `ui/` package — move it to `feature:common:component` (or
  the appropriate shared module) and drop the prefix there.

Files that stay at the package root alongside `${Name}Screen.kt`: the screen file itself, the
`${Name}ViewModel`, and `${Name}TrackingDefinitions`. Everything else routes into `model/` or
`ui/` per the rules above.

## File structure

Each screen lives in a single `${Name}Screen.kt` file with three composables:

1. **Public `${Name}Screen`** — the only composable that touches the `ViewModel`. Receives:
    - the `viewModel` (constructed by the nav graph),
    - navigation lambdas (`onLogin`, `onAddToWatchCollection`, …) — **never a `NavController`**,
    - any other arguments needed (e.g. `navigationIcon: @Composable () -> Unit`).

   It collects state with `collectAsStateWithLifecycle()` and forwards everything to the private
   `Screen`. It maps the screen's sealed `Action` to either ViewModel calls (state-changing actions)
   or navigation lambdas (navigation actions).

2. **Private `Screen`** — applies the screen scaffold (e.g. `C24DefaultScreen`) and switches on the
   sealed state to render `Loading` / `Error` / `Content`. Receives a single
   `onAction: (Action) -> Unit` callback. **Previews call this function** (not `Content`), so it
   must be free of external dependencies beyond its parameters and `CompositionLocal`s.

3. **Private `Content`** — receives raw data (the `Content` payload) plus `onAction`. Keep it
   lightweight: it composes the screen's sub-components and wires tracking. The actual UI belongs in
   sub-component composables in **separate files** (see e.g. `WatchScannerDetailsContent` in the
   `ui/` package alongside the reference screen). Tracking calls (see below) belong here.

## State and actions

- State is a sealed type (`${Name}State.Loading | .Error | .Content`) exposed by the ViewModel as
  `StateFlow` (private `MutableStateFlow` inside the ViewModel).
- UI events go through one `sealed class ${Name}Action` with a single `onAction: (Action) -> Unit`
  callback. The public `Screen` is responsible for routing each action to the ViewModel or to a nav
  lambda.
- Action case names follow one of two forms:
    - **Gesture** — `<Subject><Gesture>` where gesture ∈
      `Click | LongClick | Swipe | Select | Submit | Dismiss | Toggle`. Examples: `FavoriteClick`,
      `ChartFilterSelect`, `ImagePagerSwipe`.
    - **Imperative** — bare verb when the action carries an intent of its own rather than reporting
      a tap. Examples: `Reload`, `Logout`, `OpenCheckout(uri)`.

  Drop redundant `Button` / `Icon` from the subject (`BuyClick`, not `BuyButtonClick`).
  Going-forward
  rule — no blanket renaming of existing `*Action.kt` files.

## Tracking — screen-side, NOT through the ViewModel

Tracking through the ViewModel is the old pattern and is being phased out. Do not add tracking calls
to ViewModels in new or refactored code.

The new pattern: each screen has a sibling `${Name}TrackingDefinitions.kt` file that defines
tracking functions as `internal` extensions on `Tracker` and `ScreenTrackingScope`
(from `feature:common:core` `tracking`).

```kotlin
internal fun ScreenTrackingScope.trackXxxScreen(/* params */) = trackScreenView(
    screen = TrackingScreenDo.Xxx,
) {
    /* configure */
}

internal fun Tracker.trackXxxClick() = trackEvent(
    eventName = TrackingEventNameDo.InteractButton,
) {
    context = TrackingContextDo.Xxx
    type = TrackingTypeDo.Click
}
```

Wire the screen-view event from inside `Content` (so it only fires when content is actually shown):

```kotlin
ScreenTracker {
    trackXxxScreen(/* params from state/origin */)
}
```

Click/event tracking is invoked through the `Tracker` extensions at the call site of the action
handler.

## ViewModel

- ViewModels extend `C24ViewModel`.
- Reusable cross-feature VM logic is shared via `component<>()` from `feature:viewmodelcomponent` (
  e.g. `PerformanceChartViewModelComponent`).

## Previews

- Private, suffixed `Preview`, wrapped in `C24Preview { … }`.
- Use multi-preview annotations like `@PhonePreview` / `@TabletPreview`.
- Call the private `Screen` composable directly with mocked data — never the public
  `${Name}Screen` (no ViewModel available in previews).
