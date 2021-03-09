package com.example.squarerepos.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE: ViewState, EVENT: ViewEvent, INTENT:Intent>: ViewModel() {
    val intentChannel = Channel<INTENT>(Channel.UNLIMITED)
    abstract val _state: MutableStateFlow<STATE>
    val state: StateFlow<STATE>
        get() = _state

    abstract var _viewEvent: MutableStateFlow<Event<EVENT?>>
    val viewEvent: Flow<Event<EVENT?>>
        get() = _viewEvent

    init {
        viewModelScope.launch {
            handleIntents()
        }
    }

    abstract suspend fun handleIntents()
}