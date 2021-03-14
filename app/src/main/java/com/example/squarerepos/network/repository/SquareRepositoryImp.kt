package com.example.squarerepos.network.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.squarerepos.data.SquareRepository
import com.example.squarerepos.network.GithubPagingSource
import com.example.squarerepos.network.api.SquareReposAPI
import com.example.squarerepos.network.model.DetailSquareReposResponse
import com.example.squarerepos.network.model.SquareReposResponse
import kotlinx.coroutines.flow.Flow

class SquareRepositoryImp(private val api: SquareReposAPI) : SquareRepository {

    override suspend fun getDetailRepo(repo: String): DetailSquareReposResponse {
        return api.getDetailRepo(repo)
    }

    override fun getRepoList(): Flow<PagingData<SquareReposResponse>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { GithubPagingSource(api) }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }
}
