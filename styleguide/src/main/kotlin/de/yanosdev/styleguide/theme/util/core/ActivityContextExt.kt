@file:YDRevisionIn(implementedAt = "2026-04-17", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.util.core

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import de.yanosdev.annotation.YDRevisionIn

/**
 * Finds an [Activity] in a [Context] hierarchy
 *
 * Note that it is only possible to get an Activity from specific Contexts (View Context, Compose Context).
 * It does not work for Contexts outside of an Activity like Application Contexts or Service Contexts.
 *
 * Throws [IllegalStateException] if the hierarchy does not contain an Activity.
 *
 * TODO: Move this to util module
 * @return The [Activity] if the [Context] hierarchy contains an Activity
 */
fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) {
            return context
        } else {
            context = context.baseContext
        }
    }
    throw IllegalStateException("Context hierarchy does not contain an activity")
}