package com.example.squarerepos.core.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineExceptionHandler

abstract class BaseViewModel internal constructor() : ViewModel() {
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val handler = CoroutineExceptionHandler {
            context, exception -> println("Caught $exception in $context")
    }

    public override fun onCleared() {
        this.compositeDisposable.clear()
    }
}