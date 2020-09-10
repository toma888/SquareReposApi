package com.example.squarerepos.network.repository

import com.example.squarerepos.data.SquareRepository
import com.example.squarerepos.network.api.SquareReposAPI
import com.example.squarerepos.network.model.DetailSquareReposResponse
import com.example.squarerepos.network.model.SquareReposResponse
import io.reactivex.Single
import javax.inject.Inject

class SquareRepositoryImp @Inject constructor(private val api: SquareReposAPI) : SquareRepository {

    override fun getRepoList(): Single<List<SquareReposResponse>> {
        return api.getRepoList()
    }

    override fun getDetailRepo(repo: String): Single<DetailSquareReposResponse> {
        return api.getDetailRepo(repo)
    }
}