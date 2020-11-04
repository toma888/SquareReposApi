package com.example.squarerepos.domain.usecase

import com.example.squarerepos.data.SquareRepository
import com.example.squarerepos.network.model.SquareReposResponse
import javax.inject.Inject

class GetSquareReposListUseCase @Inject constructor(private val repository: SquareRepository) {
    suspend fun invoke(): List<SquareReposResponse> = repository.getRepoList()
}