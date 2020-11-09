package com.example.squarerepos.network.api

import com.example.squarerepos.network.model.DetailSquareReposResponse
import com.example.squarerepos.network.model.SquareReposResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface SquareReposAPI {
    @GET("users/square/reposs")
    suspend fun getRepoList(): List<SquareReposResponse>

    @GET("repos/square/{repo}")
    suspend fun getDetailRepo(@Path("repo") repo: String): DetailSquareReposResponse
}

