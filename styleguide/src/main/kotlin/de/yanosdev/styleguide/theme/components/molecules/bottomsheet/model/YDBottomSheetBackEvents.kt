@file:YDRevisionIn(implementedAt = "2026-04-24", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.bottomsheet.model

import android.window.BackEvent
import de.yanosdev.annotation.YDRevisionIn

internal sealed interface YDBottomSheetBackEvent {
    data class OnBackStarted(val backEvent: BackEvent) : YDBottomSheetBackEvent
    data class OnBackProgressed(val backEvent: BackEvent) : YDBottomSheetBackEvent
    data object OnBackInvoked : YDBottomSheetBackEvent
    data object OnBackCancelled : YDBottomSheetBackEvent
}