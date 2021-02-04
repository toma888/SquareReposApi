package com.example.squarerepos.ui.squarelist

import com.example.squarerepos.core.base.ViewEvent

sealed class SquareReposListViewEvent : ViewEvent {
    data class ShowToast(val message: String) : SquareReposListViewEvent()
    data class NavigateToDetail(val repoName: String) : SquareReposListViewEvent()
}