package com.example.squarerepos.data

import com.example.squarerepos.network.model.DetailSquareReposResponse
import com.example.squarerepos.network.model.SquareReposResponse
import io.reactivex.Single

interface SquareRepository{

    fun getRepoList(): Single<List<SquareReposResponse>>

    fun getDetailRepo(repo: String): Single<DetailSquareReposResponse>
}