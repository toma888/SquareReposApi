package com.example.squarerepos.core.base

import android.app.Application
import com.example.squarerepos.core.di.component.AppComponent
import com.example.squarerepos.core.di.component.DaggerAppComponent

class BaseApplication : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().build()
    }
}