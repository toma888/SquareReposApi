package com.example.squarerepos.ui.squaredetail

import com.example.squarerepos.domain.interactor.SquareReposInteractor
import com.example.squarerepos.domain.usecase.GetDetailSquareReposUseCase
import com.example.squarerepos.domain.usecase.GetSquareReposListUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

internal object DetailSquareReposModule {

    fun get() = module(override = true) {
        viewModel {
            DetailReposViewModel(get())
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
    loadKoinModules(DetailSquareReposModule.get())
}