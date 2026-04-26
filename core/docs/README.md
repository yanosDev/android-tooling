# Core Module Documentation

The `core` module provides shared Android utilities used across all other modules. It has no
dependency on any UI framework, design system, or third-party analytics/crash library — only
Android SDK and AndroidX essentials.

All declarations live under `de.yanosdev.core.*`.

---

## Contents

- [Logging](logging.md)

---

## Quick reference

### Logging

| Declaration                                                       | Package        | Description                                         |
|-------------------------------------------------------------------|----------------|-----------------------------------------------------|
| `YDLog`                                                           | `core.logging` | Registry — install a `YDLogger` here at startup     |
| `YDLogger`                                                        | `core.logging` | Schnittstelle to implement for any log backend      |
| `YDLogLevel`                                                      | `core.logging` | Severity enum: Verbose, Debug, Info, Warning, Error |
| `logDebug` / `logInfo` / `logVerbose` / `logWarning` / `logError` | `core.logging` | Top-level log functions                             |

### Navigation

| Declaration | Package                 | Description                                                                             |
|-------------|-------------------------|-----------------------------------------------------------------------------------------|
| `NavAction` | `core.navigation.model` | Base interface for one-shot navigation events; includes `Close` and `Success` built-ins |

### Utilities

| Declaration            | Package     | Description                                                                     |
|------------------------|-------------|---------------------------------------------------------------------------------|
| `Context.openUri`      | `core.util` | Opens any `Uri` via `ACTION_VIEW`; swallows `ActivityNotFoundException`         |
| `Context.setNightMode` | `core.util` | Sets app night mode; uses `UiModeManager` on API 31+, `AppCompatDelegate` below |
| `Context.findActivity` | `core.util` | Walks the `Context` hierarchy to find the enclosing `Activity`; throws if none  |
| `NightMode`            | `core.util` | Enum: `FollowSystem`, `No`, `Yes`                                               |
