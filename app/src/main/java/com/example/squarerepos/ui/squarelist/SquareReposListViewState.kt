package com.example.squarerepos.ui.squarelist

import com.example.squarerepos.core.base.ViewState
import com.example.squarerepos.ui.recyclerview.model.SquareReposListDisplayItem

sealed class SquareReposListViewState : ViewState {
    object Loading : SquareReposListViewState()
    data class Success(val data: MutableList<SquareReposListDisplayItem>) : SquareReposListViewState()
    object Error: SquareReposListViewState()
}