package com.example.squarerepos.ui.squaredetail

import com.example.squarerepos.core.base.Intent

sealed class DetailReposIntent : Intent{
    data class OnStart(val repoName: String) : DetailReposIntent()
}