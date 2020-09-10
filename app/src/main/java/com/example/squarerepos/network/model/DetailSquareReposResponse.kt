package com.example.squarerepos.network.model

import com.google.gson.annotations.SerializedName

data class DetailSquareReposResponse(
    val id: Long, val name: String,
    @SerializedName("stargazers_count")
    val starCount: Int,
    @SerializedName("forks_count")
    val forkCount: Int,
    @SerializedName("full_name")
    val fullName: String,
    val description: String?,
    @SerializedName("html_url")
    val url: String,
    val language: String?,
    @SerializedName("subscribers_count")
    val watcherCount: Int,
    @SerializedName("open_issues_count")
    val issuesCount: Int
)