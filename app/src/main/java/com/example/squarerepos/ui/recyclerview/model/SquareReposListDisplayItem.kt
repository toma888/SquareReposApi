package com.example.squarerepos.ui.recyclerview.model

data class SquareReposListItem(
    var idSquareRepos: Long = 0,
    var name: String = "",
    var starCount: Int = 0,
    var forkCount: Int = 0
)
