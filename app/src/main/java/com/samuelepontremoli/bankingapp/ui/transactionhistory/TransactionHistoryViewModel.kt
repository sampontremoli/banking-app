package com.samuelepontremoli.bankingapp.ui.transactionhistory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.samuelepontremoli.bankingapp.common.BaseViewModel
import com.samuelepontremoli.data.network.Response
import com.samuelepontremoli.data.network.Status
import com.samuelepontremoli.bankingapp.models.mappers.AccountMapper
import com.samuelepontremoli.bankingapp.models.Account
import com.samuelepontremoli.data.repository.AccountRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TransactionHistoryViewModel(
    private val repository: AccountRepository,
    private val mapper: AccountMapper
) : BaseViewModel() {

    private var transactionHistory = MutableLiveData<Response<Account>>()

    override fun fetchData() {
        showLoading()
        val disposable = repository.getAccount()
            .flatMap { mapper.Flowable(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                transactionHistory.value =
                    Response(
                        responseType = Status.SUCCESSFUL,
                        data = response
                    )
            }, { error ->
                error.printStackTrace()
                transactionHistory.value =
                    Response(
                        responseType = Status.ERROR,
                        error = Error(error.message)
                    )
            }, {})

        addDisposable(disposable)
    }

    private fun showLoading() {
        transactionHistory.value = Response(responseType = Status.LOADING)
    }

    fun getTransactionHistory(): LiveData<Response<Account>> = transactionHistory

    companion object {
        val TAG = TransactionHistoryViewModel::class.java.simpleName
    }

}