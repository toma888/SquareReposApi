package com.example.squarerepos.core.di.component

import dagger.Component
import com.example.squarerepos.core.base.BaseFragment
import com.example.squarerepos.core.di.module.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class])
interface AppComponent {
    fun inject(baseFragment: BaseFragment)
}