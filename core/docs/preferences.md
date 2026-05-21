# Preferences

DataStore-backed preferences with property-delegation syntax. Reads are synchronous
(in-memory cache), writes are async and return immediately.

---

## Setup

Pass an application-scoped `DataStore<Preferences>` and `CoroutineScope` — typically from DI:

```kotlin
@Singleton
class AppPreferences @Inject constructor(
    @ApplicationContext context: Context,
    @ApplicationScope scope: CoroutineScope,
) : YDPreferences(
    dataStore = context.createDataStore("app_preferences"),
    scope = scope,
) {
    var isOnboarded: Boolean by preference(booleanPreferencesKey("is_onboarded"), default = false)
    var email: String? by nullablePreference(stringPreferencesKey("email"))
    var sessionToken: String? by nullablePreference(stringPreferencesKey("session_token"))
}
```

---

## Reading and writing

```kotlin
// Read (synchronous, from in-memory cache)
val email = prefs.email

// Write (async, returns immediately)
prefs.email = "user@example.com"

// Clear a non-nullable preference back to its default
prefs.isOnboardedItem.clear()

// Clear a nullable preference (equivalent to setting null)
prefs.email = null
```

---

## Reactive observation

Each `YDPreferenceItem` exposes a `flow` for use with `collectAsState` or `collect`:

```kotlin
// Expose item alongside the delegate to get access to .flow
val emailItem = nullablePreference(stringPreferencesKey("email"))
var email: String? by emailItem

// In a ViewModel or Composable
prefs.emailItem.flow.collectAsState()
```

---

## Supported key types

Use the standard DataStore key constructors from `androidx.datastore.preferences.core`:

| Type      | Key constructor              |
|-----------|------------------------------|
| `String`  | `stringPreferencesKey("…")`  |
| `Int`     | `intPreferencesKey("…")`     |
| `Long`    | `longPreferencesKey("…")`    |
| `Float`   | `floatPreferencesKey("…")`   |
| `Double`  | `doublePreferencesKey("…")`  |
| `Boolean` | `booleanPreferencesKey("…")` |

---

## API reference

| Declaration        | Description                                                                                  |
|--------------------|----------------------------------------------------------------------------------------------|
| `YDPreferences`    | Abstract base class — extend and declare properties with `preference` / `nullablePreference` |
| `YDPreferenceItem` | Delegate holding the `StateFlow` cache; also exposes `.flow` and `.clear()`                  |
