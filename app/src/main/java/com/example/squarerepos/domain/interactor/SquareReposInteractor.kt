package com.example.squarerepos.domain.interactor

import androidx.paging.PagingData
import com.example.squarerepos.domain.usecase.GetDetailSquareReposUseCase
import com.example.squarerepos.domain.usecase.GetSquareReposListUseCase
import com.example.squarerepos.network.model.DetailSquareReposResponse
import com.example.squarerepos.network.model.SquareReposResponse
import kotlinx.coroutines.flow.Flow

class SquareReposInteractor(
    private val getSquareReposList: GetSquareReposListUseCase,
    private val getDetailSquareRepos: GetDetailSquareReposUseCase
) {
    fun getSquareReposList(): Flow<PagingData<SquareReposResponse>> = getSquareReposList.invoke()

    suspend fun getDetailSquareRepos(repo: String): DetailSquareReposResponse =
        getDetailSquareRepos.invoke(GetDetailSquareReposUseCase.Request(repo))
}