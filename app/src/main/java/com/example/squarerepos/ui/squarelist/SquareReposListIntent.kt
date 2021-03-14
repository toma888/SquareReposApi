package com.example.squarerepos.ui.squarelist

import com.example.squarerepos.core.base.Intent

sealed class SquareReposListIntent : Intent{
    object OnStart : SquareReposListIntent()
    data class OnDetailClicked(val repoName: String) : SquareReposListIntent()
}