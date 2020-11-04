package com.example.squarerepos.network.repository

import com.example.squarerepos.data.SquareRepository
import com.example.squarerepos.network.api.SquareReposAPI
import com.example.squarerepos.network.model.DetailSquareReposResponse
import com.example.squarerepos.network.model.SquareReposResponse
import javax.inject.Inject

class SquareRepositoryImp @Inject constructor(private val api: SquareReposAPI) : SquareRepository {

    override suspend fun getRepoList(): List<SquareReposResponse> {
        return api.getRepoList()
    }

    override suspend fun getDetailRepo(repo: String): DetailSquareReposResponse {
        return api.getDetailRepo(repo)
    }
}