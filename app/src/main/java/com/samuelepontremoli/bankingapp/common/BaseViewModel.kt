package com.samuelepontremoli.bankingapp.common

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Base ViewModel: implemented by specific ViewModels to ha ndle UI data in a lifecycle-aware way
 * Contains an RX CompositeDisposable to hold and clear instances of Disposable RX objects
 */
abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    private fun clearDisposables() {
        compositeDisposable.clear()
    }

    override fun onCleared() {
        clearDisposables()
    }

    abstract fun fetchData()

}