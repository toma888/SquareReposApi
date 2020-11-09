package com.example.squarerepos.network.repository

import com.example.squarerepos.data.SquareRepository
import com.example.squarerepos.network.ResultWrapper
import com.example.squarerepos.network.api.SquareReposAPI
import com.example.squarerepos.network.model.DetailSquareReposResponse
import com.example.squarerepos.network.model.SquareReposResponse
import com.example.squarerepos.network.safeApiCall

class SquareRepositoryImp(private val api: SquareReposAPI) : SquareRepository {

    override suspend fun getRepoList(): ResultWrapper<List<SquareReposResponse>> {
        return safeApiCall { api.getRepoList() }
    }

    override suspend fun getDetailRepo(repo: String): DetailSquareReposResponse {
        return api.getDetailRepo(repo)
    }
}
