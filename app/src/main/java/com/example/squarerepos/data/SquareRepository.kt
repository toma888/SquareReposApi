package com.example.squarerepos.data

import com.example.squarerepos.network.model.DetailSquareReposResponse
import com.example.squarerepos.network.model.SquareReposResponse

interface SquareRepository{

    suspend fun getRepoList(): List<SquareReposResponse>

    suspend fun getDetailRepo(repo: String): DetailSquareReposResponse
}