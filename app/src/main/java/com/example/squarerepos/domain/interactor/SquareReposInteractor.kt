package com.example.squarerepos.domain.interactor

import com.example.squarerepos.domain.usecase.GetDetailSquareReposUseCase
import com.example.squarerepos.domain.usecase.GetSquareReposListUseCase
import com.example.squarerepos.network.model.DetailSquareReposResponse
import com.example.squarerepos.network.model.SquareReposResponse
import io.reactivex.Single
import javax.inject.Inject

class SquareReposInteractor @Inject constructor(
    private val getSquareReposList: GetSquareReposListUseCase,
    private val getDetailSquareRepos: GetDetailSquareReposUseCase
) {
    fun getSquareReposList(): Single<List<SquareReposResponse>> = getSquareReposList.invoke()

    fun getDetailSquareRepos(repo: String): Single<DetailSquareReposResponse> =
        getDetailSquareRepos.invoke(GetDetailSquareReposUseCase.Request(repo))
}