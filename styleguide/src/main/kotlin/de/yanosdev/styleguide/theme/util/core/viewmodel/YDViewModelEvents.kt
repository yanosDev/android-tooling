@file:YDRevisionIn(implementedAt = "2026-04-20", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.util.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.yanosdev.annotation.YDRevisionIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

typealias YDViewModelEvents<T> = Flow<T>

interface YDMutableViewModelEvents<T> : YDViewModelEvents<T> {
    fun send(event: T)
}

private class YDMutableViewModelEventsImpl<T>(
    private val viewModelScope: CoroutineScope,
    private val channel: Channel<T> = Channel(Channel.BUFFERED)
) : YDMutableViewModelEvents<T>, Flow<T> by channel.receiveAsFlow() {
    override fun send(event: T) {
        viewModelScope.launch {
            channel.send(event)
        }
    }
}

fun <T> YDMutableViewModelEvents<T>.asImmutable() = object : YDViewModelEvents<T> by this {}


fun <T> ViewModel.ydMutableEvents(): YDMutableViewModelEvents<T> =
    YDMutableViewModelEventsImpl(viewModelScope = viewModelScope)

fun YDMutableViewModelEvents<Unit>.send() = send(Unit)