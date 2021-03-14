package com.example.squarerepos.domain.usecase

import androidx.paging.PagingData
import com.example.squarerepos.data.SquareRepository
import com.example.squarerepos.network.ResultWrapper
import com.example.squarerepos.network.model.SquareReposResponse
import kotlinx.coroutines.flow.Flow

class GetSquareReposListUseCase(private val repository: SquareRepository) {
    fun invoke(): Flow<PagingData<SquareReposResponse>> = repository.getRepoList()
}