package de.yanosdev.tooling.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

internal sealed interface YDStyleNavKey : NavKey {

    @Serializable
    data object Home : YDStyleNavKey

    @Serializable
    data object Text : YDStyleNavKey

    @Serializable
    data object TextButtons : YDStyleNavKey
}