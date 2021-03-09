package com.example.squarerepos.ui.squaredetail

import com.example.squarerepos.core.base.ViewEvent

sealed class DetailReposViewEvent : ViewEvent {
    data class ShowToast(val message: String) : DetailReposViewEvent()
}