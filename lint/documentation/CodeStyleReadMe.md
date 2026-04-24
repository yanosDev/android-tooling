# Code Style Lint Rules

Custom lint detectors enforcing code style conventions across the `de.yanosdev.*` codebase.

## YDComposableParameterSorting — ERROR

`@Composable` function parameters must follow this exact order:

1. `nav*` lambdas — navigation callbacks
2. `ViewModel` parameters — types extending `ViewModel`
3. Required parameters — non-optional, non-lambda, non-modifier (alphabetical)
4. `modifier: Modifier = Modifier` — exactly one, always optional
5. Optional parameters — with default values (non-lambdas alphabetical, then lambdas alphabetical)
6. `content: @Composable () -> Unit` — trailing composable lambda, must be named `content`, must be last

Additional constraints:

- Only one `Modifier` parameter allowed; must default to `Modifier`
- Non-content lambdas must be named with `on` prefix (actions) or `nav` prefix (navigation), or be `@Composable`
- The trailing composable lambda must be named exactly `content`

To suppress a false positive: `@SuppressLint("YDComposableParameterSorting")`

---

## DataClassParameterSorting — WARNING

`data class` constructor parameters must be sorted:

- Required parameters (no default) before optional parameters (with default)
- Alphabetical within each group
- Exception: size token names (`zero`, `extraTiny`, `tiny`, `small`, `medium`, `large`, `big`, `huge`, `extraHuge`) are
  sorted in semantic size order, not alphabetically

To suppress: `@SuppressLint("DataClassParameterSorting")`

---

## NamedArgument — WARNING

All call-site arguments to functions in `de.yanosdev.*` packages must use named arguments.

```kotlin
// correct
YDButton(text = "Hello", onClick = {}, modifier = Modifier)

// violation
YDButton("Hello", {}, Modifier)
```

Exception: the trailing `content` lambda in trailing-lambda syntax does not need to be named.