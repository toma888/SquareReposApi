package com.example.squarerepos.core.base

import android.app.Application
import com.example.squarerepos.core.di.module.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initDependencies()
    }

    private fun initDependencies() {
        startKoin {
            androidContext(this@BaseApplication)
            modules(
                listOf(
                    appModule
                )
            )
        }
    }
}