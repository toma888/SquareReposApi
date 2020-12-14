package com.example.squarerepos.ui.squarelist

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.squarerepos.core.base.BaseViewModel
import com.example.squarerepos.data.mapper.toDisplaySquareRepos
import com.example.squarerepos.domain.interactor.SquareReposInteractor
import com.example.squarerepos.ui.recyclerview.model.SquareReposListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SquareReposListViewModel(
    private val interactor: SquareReposInteractor
) : BaseViewModel() {

    fun loadReposList(): Flow<PagingData<SquareReposListItem>> {
        return interactor.getSquareReposList()
            .map { pagingData -> pagingData.map { it.toDisplaySquareRepos() } }
            .cachedIn(viewModelScope)
    }
}