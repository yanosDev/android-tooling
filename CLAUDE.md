# CLAUDE.md — Rules for Claude Code in this repo

This file defines the rules, conventions, and lint constraints Claude must follow when working in this repository. Treat
every section as a hard constraint unless a user instruction in the active conversation explicitly overrides it.

---

## Project overview

Android monorepo distributed via JitPack. Modules:

| Module                              | Purpose                                               |
|-------------------------------------|-------------------------------------------------------|
| `styleguide`                        | Compose design system (atoms → molecules → organisms) |
| `lint`                              | Custom lint detectors + annotation processors         |
| `core`                              | Shared Android utilities                              |
| `toolingapp`                        | Internal preview/demo app for the styleguide          |
| `file-templates` / `live-templates` | IDE template plugins                                  |

---

## Enforced lint rules — never violate these

The following detectors run as part of the build. Violations are **errors** (or warnings that block CI). Never produce
code that would trigger them.

### 1. `YDComposableParameterSorting` — ERROR

Every `@Composable` function must order parameters in this exact sequence:

```
1. nav* lambdas          (name starts with "nav")
2. ViewModel parameters  (type extends ViewModel)
3. Required parameters   (non-optional, non-lambda, non-modifier — alphabetical within group)
4. modifier: Modifier = Modifier  (exactly one, always optional)
5. Optional parameters   (have default values — non-lambdas alphabetical, then lambdas alphabetical)
6. content: @Composable () -> Unit  (trailing lambda — must be named "content", must be last)
```

Additional rules enforced by the detector:

- There can be **only one** `Modifier` parameter, and it **must** have a default value of `Modifier`.
- Non-content lambdas must be named with prefix `on` (action lambdas) or `nav` (navigation lambdas), or be
  `@Composable`.
- The trailing composable lambda must be named exactly `content`.

To suppress legitimately when the detector is wrong, use `@SuppressLint("YDComposableParameterSorting")` — do not
reorder parameters to suppress a false positive.

### 2. `DataClassParameterSorting` — WARNING

Parameters of `data class` constructors must be sorted:

- Required parameters (no default) before optional parameters (with default).
- Within each group: **alphabetical** by parameter name.
- Exception: parameters whose names are size tokens (`zero`, `extraTiny`, `tiny`, `small`, `medium`, `large`, `big`,
  `huge`, `extraHuge`) are sorted in that semantic size order, not alphabetically.
- To suppress: `@SuppressLint("DataClassParameterSorting")` on the class.

### 3. `NamedArgument` — WARNING

All call-site arguments to functions in the `de.yanosdev.*` package must be **named arguments**. The only exception is
the trailing `content` lambda when it is the last argument and written in trailing-lambda syntax.

Always write:

```kotlin
YDButton(text = "Hello", onClick = {}, modifier = Modifier)
```

Not:

```kotlin
YDButton("Hello", {}, Modifier)
```

### 4. `YDRevisionMissing` — WARNING

Every `.kt` file must have a `@file:YDRevisionIn` annotation at the top. When creating or editing a file that is missing
it, add:

```kotlin
@file:YDRevisionIn(implementedAt = "YYYY-MM-DD", revisionAfterInDays = 365)
```

Use today's date (`currentDate` from context) for `implementedAt`. Import: `de.yanosdev.annotation.YDRevisionIn`.

### 5. `YDRevisionDue` — WARNING

Files where the revision date has passed must be reviewed. Do not update the `implementedAt` date without actually
reviewing the file.

---

## Styleguide architecture

The `styleguide` module follows Atomic Design inside `de.yanosdev.styleguide.theme`:

```
foundations/
  token/       — raw design values (colors, shadows, typography, alphas)
  semantics/   — semantic wrappers + CompositionLocals (YDColors, YDTypography, …)
themes/        — YDTheme object, YDRootTheme, YDRippleTheme
components/
  atoms/       — smallest primitives (YDButton, YDText, YDIcon, YDSurface, …)
  molecules/   — composed atoms (YDPrimaryButton, YDTabRow, YDScaffold, …)
  organisms/   — full-screen patterns (YDTopAppBar, YDDefaultScreen, …)
util/          — preview helpers, window size utilities, touch target enforcement
```

Package for all new styleguide code: `de.yanosdev.styleguide.theme.*`  
Never use a different root package (e.g. `com.chrono24.*`) for files in this module.

---

## Code conventions

### Comments

- **Remove** comments that describe *what* the code does — well-named identifiers do that.
- **Keep** comments that explain *why*: hidden constraints, workarounds, non-obvious math, suppressed lint with reason.
- **Keep** `// TODO:` comments as-is.
- **Write KDoc** (`/** */`) on every public `@Composable`, `class`, `data class`, and top-level `fun`. At
  minimum document: non-obvious parameter semantics, state ownership (internal vs. caller-controlled),
  and callback contracts (when it is called, what it receives). Omit KDoc only when every parameter is
  fully self-explanatory from its name and type alone.
- **Remove** region markers (`//region`, `//endregion`) and section-label comments (`// Tab specifications`,
  `// Indeterminate circular indicator transition specs`, etc.).

### Visibility modifiers

- Default to the most restrictive visibility that works. When creating new files, default every declaration to `private`
  or `internal` unless there is a concrete reason it must be public (e.g. it is in a public function's parameter type
  position, or it is a UI composable intended for consumers).
- Internal implementation details (scaffold slots, defaults objects used only within the module, etc.) should be
  `internal`.
- Prefer `private` over `internal` for things used only within a single file.
- `@Composable` color/token helper functions shared only between files in the same package should be `internal`.

### Naming

- Object property constants: `PascalCase` (e.g. `val Small`, `val Medium` — not `val small`, `val medium`).
- Composable preview functions: `PascalCase` ending in `Preview` (e.g. `private fun SelectedPreview()` — not
  `YD_Tab_Selected_Preview`).
- Token objects: internal, suffix `Tokens` (e.g. `YDColorTokens`, `YDShadowTokens`).
- Defaults objects: suffix `Defaults` (e.g. `YDButtonDefaults`). Public if consumed externally, `internal` if only used
  inside the module.

### File structure

- One top-level class, interface, object, or enum per file. Private/internal helpers closely tied to that declaration
  may live in the same file, but avoid placing two independently-meaningful types in one file.

### Kotlin style

- Use expression body (`= `) for single-expression functions.
- Use named arguments at all call sites within `de.yanosdev.*` code (enforced by lint).
- No trailing whitespace or stray blank lines at end of file.
- No `//endregion` or `//region` markers.

### Data classes

- Annotate with `@SuppressLint("DataClassParameterSorting")` only when the parameter order is intentionally semantic (
  e.g. size scales). Otherwise keep alphabetical order.
- Always annotate with `@Immutable` or `@Stable` where applicable in Compose context.

---

## Do not change without asking

- Parameter order of existing public `@Composable` functions — the detector enforces a specific order and callers depend
  on it. Changing breaks binary compatibility.
- `@SuppressLint` annotations already present — they are intentional.
- Token values in `YDColorTokens`, `YDShadowTokens`, `YDTypographyTokens` — these map 1:1 to design specs.
- `YDColors` field order and `@SuppressLint("DataClassParameterSorting")` suppression — intentional semantic grouping.

---

## When creating new styleguide components

1. Place atoms in `components/atoms/<group>/`, molecules in `components/molecules/<group>/`, organisms in
   `components/organisms/<group>/`.
2. Companion `*Defaults` and `*Colors` objects go in the same package as the component.
3. Add `@file:YDRevisionIn(implementedAt = "<today>", revisionAfterInDays = 365)` at the top.
4. Add a `@PhonePreview` + `@Composable private fun Preview()` at the bottom of each component file.
5. Use `YDPreview { }` as the preview wrapper.
6. Never import `androidx.compose.material3` theme tokens directly; always go through `YDTheme.*`.
7. Add an entry to `styleguide/docs/atoms.md`, `molecules.md`, or `organisms.md` for every new public
   component. Each entry must include: a one-line description and at least one usage snippet. Update the
   entry whenever the public API changes.
