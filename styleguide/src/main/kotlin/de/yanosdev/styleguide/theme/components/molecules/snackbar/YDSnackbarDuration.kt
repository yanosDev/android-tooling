@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.snackbar

import de.yanosdev.annotation.YDRevisionIn

/** Controls how long a snackbar remains visible before auto-dismissing. */
enum class YDSnackbarDuration {
    /** Stays for approximately 4 seconds. */
    Short,

    /** Stays for approximately 10 seconds. */
    Long,

    /** Remains until explicitly dismissed by the caller or the user. */
    Indefinite,
}
