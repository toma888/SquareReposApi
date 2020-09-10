package com.example.squarerepos.core.di.module

import androidx.lifecycle.ViewModel
import com.example.squarerepos.core.base.ViewModelFactory
import com.example.squarerepos.domain.interactor.SquareReposInteractor
import com.example.squarerepos.ui.squaredetail.DetailReposViewModel
import com.example.squarerepos.ui.squarelist.SquareReposListViewModel
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider
import kotlin.reflect.KClass

@Module(includes = [InteractorModule::class])
class ViewModelModule {

    @Target(
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER
    )
    @MapKey
    annotation class ViewModelKey(val value: KClass<out ViewModel>)

    @Provides
    fun viewModelFactory(
        providerMap: Map<Class<out ViewModel>,
                @JvmSuppressWildcards Provider<ViewModel>>
    ): ViewModelFactory =
        ViewModelFactory(providerMap)

    @Provides
    @IntoMap
    @ViewModelKey(SquareReposListViewModel::class)
    fun bindsSquareReposListViewModel(interactor: SquareReposInteractor): ViewModel =
        SquareReposListViewModel(interactor)

    @Provides
    @IntoMap
    @ViewModelKey(DetailReposViewModel::class)
    fun bindsDetailReposViewModel(interactor: SquareReposInteractor): ViewModel =
        DetailReposViewModel(interactor)
}