package com.example.squarerepos.core.di.module

import com.example.squarerepos.data.SquareRepository
import com.example.squarerepos.domain.usecase.GetDetailSquareReposUseCase
import com.example.squarerepos.domain.usecase.GetSquareReposListUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class])
class UseCaseModule {
    @Singleton
    @Provides
    fun getSquareRepoListUseCase(repository: SquareRepository): GetSquareReposListUseCase =
        GetSquareReposListUseCase(repository)

    @Singleton
    @Provides
    fun getDetailSquareReposUseCase(repository: SquareRepository): GetDetailSquareReposUseCase =
        GetDetailSquareReposUseCase(repository)
}