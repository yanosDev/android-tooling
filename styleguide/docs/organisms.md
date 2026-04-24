# Organisms

Full-screen patterns that wire UI architecture together. Import from
`de.yanosdev.styleguide.theme.components.organisms.*`.

---

## YDNavigationUpIcon / YDNavigationCloseIcon

Pre-built icon buttons for standard navigation actions. Pass directly to `YDTopAppBar`'s `navigationIcon` slot.

```kotlin
// Back arrow — use when navigating up the back stack
YDNavigationUpIcon(navUp = { navController.navigateUp() })

// Close X — use when dismissing a modal/sheet
YDNavigationCloseIcon(navUp = { navController.popBackStack() })
```

---

## YDDefaultScreen

Opinionated full-screen layout: `YDScaffold` + `YDTopAppBar` + navigation icon wired together in a single call.
Use this for the majority of detail/sub screens.

```kotlin
YDDefaultScreen(
    navBack = { YDNavigationUpIcon(navUp = navBack) },
    title = "My Screen",
    actions = {
        YDIconButton(onClick = onShare) {
            YDIcon(imageVector = Icons.Rounded.Share, contentDescription = "Share")
        }
    },
) { contentPadding ->
    LazyColumn(contentPadding = contentPadding) {
        // items
    }
}
```

---

## UIState

A sealed interface that models the three states every screen can be in.

```kotlin
interface UIState<out T> {
    data class Content<T>(val data: T, val isRefreshing: Boolean = false) : UIState<T>
    data class Error(val failure: String) : UIState<Nothing>
    data object Loading : UIState<Nothing>
}
```

Convert any domain object into a `UIState.Content` with the extension:

```kotlin
val state: UIState<MyData> = myData.toUIContent()
```

---

## YDViewModel

Interface that every screen ViewModel must implement. It exposes a `StateFlow<UIState<T>>` and accepts typed
action events `Z` from the UI.

```kotlin
interface YDViewModel<T, Z> {
    val navEvents: YDViewModelEvents<NavAction>
    val state: StateFlow<T>

    fun onAction(action: Z)
    fun onNavAction(action: NavAction)
}
```

Typical implementation in a `ViewModel`:

```kotlin
class MyViewModel : ViewModel(), YDViewModel<UIState<MyData>, MyAction> {

    private val _state = MutableStateFlow<UIState<MyData>>(UIState.Loading)
    override val state: StateFlow<UIState<MyData>> = _state.asStateFlow()

    private val _navEvents = ydMutableEvents<NavAction>()
    override val navEvents: YDViewModelEvents<NavAction> = _navEvents.asImmutable()

    override fun onAction(action: MyAction) { /* handle UI actions */ }

    override fun onNavAction(action: NavAction) {
        _navEvents.send(event = action)
    }
}
```

---

## YDViewModelEvents

A `Flow`-based one-shot event channel for navigation. Use `ydMutableEvents()` inside a `ViewModel` to create the
backing channel and expose it via `.asImmutable()`.

```kotlin
// Inside ViewModel
private val _navEvents = ydMutableEvents<NavAction>()
override val navEvents: YDViewModelEvents<NavAction> = _navEvents.asImmutable()

// Emit a navigation event
_navEvents.send(event = NavAction.NavigateUp)
```

---

## YDUIContent / YDUIContentScope

`YDUIContent` is the primary architectural composable. It collects `viewModel.state`, animates transitions between
states, and renders the `content` lambda only when the state is `UIState.Content`. Inside the lambda, the receiver
`YDUIContentScope<T, Z>` provides access to the screen data and action dispatchers.

```kotlin
// Pattern: pair with YDDefaultScreen
YDDefaultScreen(
    navBack = { YDNavigationUpIcon(navUp = navBack) },
    title = "My Screen",
) { contentPadding ->
    YDUIContent(viewModel = viewModel) {
        // `data` is T (your content type), resolved from UIState.Content
        // `onAction` sends Z events to the ViewModel
        // `onNavAction` sends NavAction events
        LazyColumn(contentPadding = contentPadding) {
            items(data.items) { item ->
                ItemRow(
                    item = item,
                    onClick = { onAction(MyAction.Select(item.id)) },
                )
            }
        }
    }
}
```

The scope interface:

```kotlin
interface YDUIContentScope<T, Z> {
    val data: T
    fun onAction(action: Z)
    fun onNavAction(action: NavAction)
}
```
