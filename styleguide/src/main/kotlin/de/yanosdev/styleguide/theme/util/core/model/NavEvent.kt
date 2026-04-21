@file:YDRevisionIn(implementedAt = "2026-04-20", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.util.core.model

import de.yanosdev.annotation.YDRevisionIn

interface NavAction {
    data object Close : NavAction
    data object Success : NavAction
}