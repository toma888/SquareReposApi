package com.example.squarerepos.domain.usecase

import com.example.squarerepos.data.SquareRepository
import com.example.squarerepos.network.model.DetailSquareReposResponse
import io.reactivex.Single
import javax.inject.Inject

class GetDetailSquareReposUseCase @Inject constructor(private val repository: SquareRepository) :
    Function1<GetDetailSquareReposUseCase.Request, Single<DetailSquareReposResponse>> {

    override fun invoke(request: Request): Single<DetailSquareReposResponse> =
        repository.getDetailRepo(request.repo)

    data class Request(val repo: String)
}