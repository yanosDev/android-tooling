@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.snackbar

import de.yanosdev.annotation.YDRevisionIn

/** The result returned by [YDSnackbarHostState.showSnackbar] after the snackbar is gone. */
enum class YDSnackbarResult {
    /** The snackbar timed out or was dismissed without the action being tapped. */
    Dismissed,

    /** The user tapped the action button. */
    ActionPerformed,
}
