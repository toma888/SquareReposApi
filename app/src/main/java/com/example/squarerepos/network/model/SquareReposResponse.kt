package com.example.squarerepos.network.model

import com.google.gson.annotations.SerializedName

data class SquareReposResponse(
    val id: Long, val name: String,
    @SerializedName("stargazers_count")
    val starCount: Int,
    @SerializedName("forks_count")
    val forkCount: Int
)