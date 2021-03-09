package com.example.squarerepos.core.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class BaseFragment<INTENT : Intent, STATE : ViewState, EVENT : ViewEvent,
        VM : BaseViewModel<STATE, EVENT, INTENT>>(@LayoutRes layout: Int) : Fragment(layout) {
    abstract val viewModel: VM

    open fun addObservers() {
        viewModel.state
            .onEach { renderState(it) }
            .launchIn(lifecycleScope)

        viewModel.viewEvent
            .onEachEvent { renderEvent(it) }
            .launchIn(lifecycleScope)
    }

    abstract fun renderState(state: STATE)

    abstract fun renderEvent(event: EVENT)
}