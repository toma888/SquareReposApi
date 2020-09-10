package com.example.squarerepos.domain.usecase

import com.example.squarerepos.data.SquareRepository
import com.example.squarerepos.network.model.SquareReposResponse
import io.reactivex.Single
import javax.inject.Inject

class GetSquareReposListUseCase @Inject constructor(private val repository: SquareRepository) :
    Function0<Single<List<SquareReposResponse>>> {

    override fun invoke(): Single<List<SquareReposResponse>> = repository.getRepoList()
}