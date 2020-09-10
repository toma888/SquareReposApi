package com.example.squarerepos.ui.squaredetail

data class DetailSquareRepos(
    val id: Long,
    val name: String,
    val starCount: String,
    val forkCount: String,
    val fullName: String,
    val description: String?,
    val url: String,
    val language: String?,
    val watcherCount: String,
    val issuesCount: String
)