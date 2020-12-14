package com.example.squarerepos.network.api

import com.example.squarerepos.network.model.DetailSquareReposResponse
import com.example.squarerepos.network.model.SquareReposResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SquareReposAPI {
    @GET("users/square/repos")
    suspend fun getRepoList(
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): List<SquareReposResponse>

    @GET("repos/square/{repo}")
    suspend fun getDetailRepo(@Path("repo") repo: String): DetailSquareReposResponse
}

