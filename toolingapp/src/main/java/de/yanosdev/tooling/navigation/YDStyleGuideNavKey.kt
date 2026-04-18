@file:YDRevisionIn(implementedAt = "2026-04-17", revisionAfterInDays = 365)

package de.yanosdev.tooling.navigation

import androidx.navigation3.runtime.NavKey
import de.yanosdev.annotation.YDRevisionIn
import kotlinx.serialization.Serializable

internal sealed interface YDStyleGuideNavKey : NavKey {

    @Serializable
    data object Home : YDStyleGuideNavKey

    @Serializable
    data object Text : YDStyleGuideNavKey

    @Serializable
    data object TextButtons : YDStyleGuideNavKey
}