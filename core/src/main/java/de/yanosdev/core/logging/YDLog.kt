@file:YDRevisionIn(implementedAt = "2026-04-26", revisionAfterInDays = 365)

package de.yanosdev.core.logging

import de.yanosdev.annotation.YDRevisionIn

/**
 * Central log registry.
 *
 * Install a [YDLogger] once at application startup. All [logDebug], [logInfo], [logWarning],
 * [logError], and [logVerbose] calls are forwarded to it. If no logger is installed the calls
 * are silently dropped — no crash, no output.
 *
 * ```kotlin
 * // In Application.onCreate():
 * YDLog.install { level, tag, message, throwable ->
 *     Log.println(level.androidPriority(), tag, message)
 *     throwable?.let { Log.e(tag, message, it) }
 * }
 * ```
 */
object YDLog {

    @Volatile
    private var logger: YDLogger? = null

    /** Installs [logger] as the active log sink. Replaces any previously installed logger. */
    fun install(logger: YDLogger) {
        this.logger = logger
    }

    internal fun dispatch(level: YDLogLevel, tag: String, message: () -> String, throwable: Throwable? = null) {
        val l = logger ?: return
        l.log(level = level, tag = tag, message = message(), throwable = throwable)
    }
}

/** Logs a verbose-level message. [message] is evaluated lazily — not called if no logger is installed. */
fun logVerbose(tag: String, message: () -> String) =
    YDLog.dispatch(level = YDLogLevel.Verbose, tag = tag, message = message)

/** Logs a debug-level message. [message] is evaluated lazily — not called if no logger is installed. */
fun logDebug(tag: String, message: () -> String) =
    YDLog.dispatch(level = YDLogLevel.Debug, tag = tag, message = message)

/** Logs an info-level message. [message] is evaluated lazily — not called if no logger is installed. */
fun logInfo(tag: String, message: () -> String) =
    YDLog.dispatch(level = YDLogLevel.Info, tag = tag, message = message)

/** Logs a warning-level message. [message] is evaluated lazily — not called if no logger is installed. */
fun logWarning(tag: String, message: () -> String, throwable: Throwable? = null) =
    YDLog.dispatch(level = YDLogLevel.Warning, tag = tag, message = message, throwable = throwable)

/** Logs an error-level message. [message] is evaluated lazily — not called if no logger is installed. */
fun logError(tag: String, message: () -> String, throwable: Throwable? = null) =
    YDLog.dispatch(level = YDLogLevel.Error, tag = tag, message = message, throwable = throwable)
