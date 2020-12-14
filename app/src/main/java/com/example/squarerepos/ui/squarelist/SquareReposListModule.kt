package com.example.squarerepos.ui.squarelist

import com.example.squarerepos.domain.interactor.SquareReposInteractor
import com.example.squarerepos.domain.usecase.GetDetailSquareReposUseCase
import com.example.squarerepos.domain.usecase.GetSquareReposListUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

internal object SquareReposListModule {

    fun get() = module(override = true) {
        viewModel {
            SquareReposListViewModel(get())
        }
        single {
            SquareReposInteractor(
                GetSquareReposListUseCase(get()),
                GetDetailSquareReposUseCase(get())
            )
        }
    }
}

internal fun loadModule() {
    loadKoinModules(SquareReposListModule.get())
}