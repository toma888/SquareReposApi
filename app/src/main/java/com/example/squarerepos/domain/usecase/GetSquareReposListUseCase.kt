package com.example.squarerepos.domain.usecase

import com.example.squarerepos.data.SquareRepository
import com.example.squarerepos.network.ResultWrapper
import com.example.squarerepos.network.model.SquareReposResponse

class GetSquareReposListUseCase(private val repository: SquareRepository) {
    suspend fun invoke(): ResultWrapper<List<SquareReposResponse>> = repository.getRepoList()
}