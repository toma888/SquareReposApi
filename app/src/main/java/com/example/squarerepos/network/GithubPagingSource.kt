package com.example.squarerepos.network

import androidx.paging.PagingSource
import com.example.squarerepos.network.api.SquareReposAPI
import com.example.squarerepos.network.model.SquareReposResponse
import retrofit2.HttpException
import java.io.IOException

// GitHub page API is 1 based: https://developer.github.com/v3/#pagination
private const val GITHUB_STARTING_PAGE_INDEX = 1

class GithubPagingSource(
        private val service: SquareReposAPI
) : PagingSource<Int, SquareReposResponse>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SquareReposResponse> {
        val position = params.key ?: GITHUB_STARTING_PAGE_INDEX
        return try {
            val repos = service.getRepoList(position, params.loadSize)
            LoadResult.Page(
                    data = repos,
                    prevKey = if (position == GITHUB_STARTING_PAGE_INDEX) null else position - 1,
                    nextKey = if (repos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}
