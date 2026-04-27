@file:YDRevisionIn(implementedAt = "2026-04-26", revisionAfterInDays = 365)

package de.yanosdev.core.logging

import de.yanosdev.annotation.YDRevisionIn

/**
 *
 * Implement this interface to route log calls to any backend (Android Log, Crashlytics, etc.)
 * and install it once at app startup via [YDLog.install]. Core has no dependency on any logging
 * library — all routing happens in the layer that installs the implementation.
 *
 * @see YDLog
 */
interface YDLogger {

    /**
     * Called for every log event dispatched through [logDebug], [logInfo], [logWarning],
     * [logError], or [logVerbose].
     *
     * @param level Severity of the event.
     * @param tag Caller-supplied category tag.
     * @param message Already-evaluated message string.
     * @param throwable Optional associated exception; null for non-error levels.
     */
    fun log(level: YDLogLevel, tag: String, message: String, throwable: Throwable? = null)
}
