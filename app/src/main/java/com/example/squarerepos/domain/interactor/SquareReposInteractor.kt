package com.example.squarerepos.domain.interactor

import com.example.squarerepos.domain.usecase.GetDetailSquareReposUseCase
import com.example.squarerepos.domain.usecase.GetSquareReposListUseCase
import com.example.squarerepos.network.ResultWrapper
import com.example.squarerepos.network.model.DetailSquareReposResponse
import com.example.squarerepos.network.model.SquareReposResponse

class SquareReposInteractor(
    private val getSquareReposList: GetSquareReposListUseCase,
    private val getDetailSquareRepos: GetDetailSquareReposUseCase
) {
    suspend fun getSquareReposList(): ResultWrapper<List<SquareReposResponse>> = getSquareReposList.invoke()

    suspend fun getDetailSquareRepos(repo: String): ResultWrapper<DetailSquareReposResponse> =
        getDetailSquareRepos.invoke(GetDetailSquareReposUseCase.Request(repo))
}