# Logging

A lightweight logging facade. Call sites use top-level functions (`logDebug`, `logError`, etc.);
the actual backend is wired up once at app startup by installing a `YDLogger`. Core carries zero
logging dependencies — Crashlytics, Timber, or any other library lives exclusively in the
consuming layer.

---

## Architecture

```
logDebug("Tag") { "message" }
        │
        ▼
   YDLog.dispatch()          ← checks if a logger is installed; no-ops if not
        │
        ▼
   YDLogger.log(level, tag, message, throwable)   ← your implementation
        │
        ├── Android Log
        ├── Crashlytics
        └── any other sink
```

---

## Installing a logger

Call `YDLog.install` once in `Application.onCreate()`. Any module that depends on `core` can
then call the top-level functions without knowing about the backend.

```kotlin
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        YDLog.install { level, tag, message, throwable ->
            Log.println(level.androidPriority(), tag, message)
            throwable?.let { Log.e(tag, message, it) }
        }
    }
}

// Helper to map YDLogLevel → android.util.Log priority
private fun YDLogLevel.androidPriority() = when (this) {
    YDLogLevel.Verbose -> Log.VERBOSE
    YDLogLevel.Debug -> Log.DEBUG
    YDLogLevel.Info -> Log.INFO
    YDLogLevel.Warning -> Log.WARN
    YDLogLevel.Error -> Log.ERROR
}
```

### Adding Crashlytics later

Extend the lambda to forward non-fatal exceptions to Crashlytics without changing any call site:

```kotlin
YDLog.install { level, tag, message, throwable ->
    Log.println(level.androidPriority(), tag, message)
    if (level >= YDLogLevel.Warning) {
        FirebaseCrashlytics.getInstance().log("[$tag] $message")
        throwable?.let { FirebaseCrashlytics.getInstance().recordException(it) }
    }
}
```

---

## Logging at call sites

```kotlin
logVerbose(tag = "Network") { "connecting to $url" }

logDebug(tag = "HomeVM") { "items loaded: ${items.size}" }

logInfo(tag = "Auth") { "user signed in: ${user.id}" }

logWarning(tag = "Cache") { "cache miss for key $key" }

logError(tag = "Sync", throwable = e) { "sync failed after ${retries} retries" }
```

Message lambdas are **lazy** — the string is only evaluated if a logger is installed. No
string concatenation or `toString()` calls happen in production if you stripped the logger.

---

## YDLogger interface

Implement `YDLogger` to forward log events to any backend:

```kotlin
interface YDLogger {
    fun log(level: YDLogLevel, tag: String, message: String, throwable: Throwable? = null)
}
```

| Parameter   | Description                                                        |
|-------------|--------------------------------------------------------------------|
| `level`     | Severity (`Verbose`, `Debug`, `Info`, `Warning`, `Error`)          |
| `tag`       | Caller-supplied category string                                    |
| `message`   | Already-evaluated message (the lambda has been invoked by `YDLog`) |
| `throwable` | Associated exception; `null` for non-error levels                  |

---

## YDLog registry

| Member                  | Description                                         |
|-------------------------|-----------------------------------------------------|
| `YDLog.install(logger)` | Sets the active log sink; replaces any previous one |

If no logger is installed all log calls are silently dropped — no crash, no output.
