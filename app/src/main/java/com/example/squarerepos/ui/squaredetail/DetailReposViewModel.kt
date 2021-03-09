package com.example.squarerepos.ui.squaredetail

import com.example.squarerepos.core.base.BaseViewModel
import com.example.squarerepos.core.base.Event
import com.example.squarerepos.data.mapper.toDisplaySquareRepos
import com.example.squarerepos.domain.interactor.SquareReposInteractor
import com.example.squarerepos.network.ResultWrapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow

class DetailReposViewModel(
    private val interactor: SquareReposInteractor
) : BaseViewModel<DetailReposViewState, DetailReposViewEvent, DetailReposIntent>() {


    override val _state = MutableStateFlow<DetailReposViewState>(DetailReposViewState.Loading)
    override var _viewEvent = MutableStateFlow<Event<DetailReposViewEvent?>>(Event(null))


    override suspend fun handleIntents() {
        intentChannel.consumeAsFlow().collect { intent ->
            when (intent) {
                is DetailReposIntent.OnStart -> {
                    when (val detailRepos = interactor.getDetailSquareRepos(intent.repoName)) {
                        is ResultWrapper.Success -> _state.value = DetailReposViewState.Success(
                            detailRepos.data.toDisplaySquareRepos()
                        )
                        is ResultWrapper.Failure -> {
                            _viewEvent.value = Event(
                                DetailReposViewEvent.ShowToast("An error occurred: ${detailRepos.throwable.message}")
                            )
                            _state.value = DetailReposViewState.Error
                        }
                    }
                }
            }
        }
    }
}