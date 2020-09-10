package com.example.squarerepos.core.di.module

import com.example.squarerepos.domain.interactor.SquareReposInteractor
import com.example.squarerepos.domain.usecase.GetDetailSquareReposUseCase
import com.example.squarerepos.domain.usecase.GetSquareReposListUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [UseCaseModule::class])
class InteractorModule {
    @Singleton
    @Provides
    fun getSquareReposInteractor(
        getSquareRepoListUseCase: GetSquareReposListUseCase,
        getDetailSquareReposUseCase: GetDetailSquareReposUseCase
    ): SquareReposInteractor =
        SquareReposInteractor(
            getSquareRepoListUseCase,
            getDetailSquareReposUseCase
        )
}