package com.example.squarerepos.ui.squarelist

import com.example.squarerepos.core.base.BaseViewModel
import com.example.squarerepos.core.base.Event
import com.example.squarerepos.data.mapper.toDisplayListSquareRepos
import com.example.squarerepos.domain.interactor.SquareReposInteractor
import com.example.squarerepos.network.ResultWrapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow

class SquareReposListViewModel(
    private val interactor: SquareReposInteractor
) : BaseViewModel<SquareReposListViewState, SquareReposListViewEvent, SquareReposListIntent>() {

    override val _state = MutableStateFlow<SquareReposListViewState>(SquareReposListViewState.Loading)
    override var _viewEvent: MutableStateFlow<Event<SquareReposListViewEvent?>> = MutableStateFlow(Event(null))

    override suspend fun handleIntents() {
        intentChannel.consumeAsFlow().collect { intent ->
            when (intent) {
                is SquareReposListIntent.OnStart -> {
                    when (val listResponse = interactor.getSquareReposList()) {
                        is ResultWrapper.Success -> _state.value = SquareReposListViewState.Success(
                            listResponse.data.toDisplayListSquareRepos().toMutableList()
                        )
                        is ResultWrapper.Failure -> {
                            _viewEvent.value = Event(
                                SquareReposListViewEvent.ShowToast("An error occurred: ${listResponse.throwable.message}")
                            )
                            _state.value = SquareReposListViewState.Error
                        }
                    }
                }
                is SquareReposListIntent.OnDetailClicked -> _viewEvent.value = Event(
                    SquareReposListViewEvent.NavigateToDetail(intent.repoName)
                )
            }
        }
    }
}