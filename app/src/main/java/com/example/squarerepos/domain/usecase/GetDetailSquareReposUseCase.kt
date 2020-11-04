package com.example.squarerepos.domain.usecase

import com.example.squarerepos.data.SquareRepository
import com.example.squarerepos.network.model.DetailSquareReposResponse
import javax.inject.Inject

class GetDetailSquareReposUseCase @Inject constructor(private val repository: SquareRepository) {

    suspend fun invoke(request: Request): DetailSquareReposResponse =
        repository.getDetailRepo(request.repo)

    data class Request(val repo: String)
}