package com.example.squarerepos.network.api

import com.example.squarerepos.network.model.DetailSquareReposResponse
import com.example.squarerepos.network.model.SquareReposResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface SquareReposAPI {
    @GET("users/square/repos")
    fun getRepoList(): Single<List<SquareReposResponse>>

    @GET("repos/square/{repo}")
    fun getDetailRepo(@Path("repo") repo: String): Single<DetailSquareReposResponse>
}