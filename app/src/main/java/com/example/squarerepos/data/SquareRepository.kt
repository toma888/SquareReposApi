package com.example.squarerepos.data

import androidx.paging.PagingData
import com.example.squarerepos.network.model.DetailSquareReposResponse
import com.example.squarerepos.network.model.SquareReposResponse
import kotlinx.coroutines.flow.Flow

interface SquareRepository{

   fun getRepoList(): Flow<PagingData<SquareReposResponse>>

    suspend fun getDetailRepo(repo: String): DetailSquareReposResponse
}