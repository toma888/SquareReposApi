package com.example.squarerepos.ui.squaredetail

import com.example.squarerepos.core.base.ViewState
import com.example.squarerepos.ui.recyclerview.model.SquareReposListDisplayItem

sealed class DetailReposViewState : ViewState {
    object Loading : DetailReposViewState()
    data class Success(val data: DetailSquareRepos) : DetailReposViewState()
    object Error: DetailReposViewState()
}