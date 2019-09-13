package com.samuelepontremoli.bankingapp.ui.transactionhistory

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.samuelepontremoli.bankingapp.common.BaseViewModel
import com.samuelepontremoli.data.network.Response
import com.samuelepontremoli.data.network.Status
import com.samuelepontremoli.data.presentation.AccountMapper
import com.samuelepontremoli.data.presentation.Account
import com.samuelepontremoli.data.repository.AccountRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TransactionHistoryViewModel(
    private val repository: AccountRepository,
    private val mapper: AccountMapper
) : BaseViewModel() {

    private var transactionHistory = MutableLiveData<Response<Account>>()

    override fun fetchData() {
        val disposable = repository.getAccountRemote()
            .flatMap { mapper.Flowable(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                Log.d(TAG, "Success!")
                transactionHistory.value =
                    Response(
                        responseType = Status.SUCCESSFUL,
                        data = response
                    )
            }, { error ->
                Log.d(TAG, "Error!")
                error.printStackTrace()
                transactionHistory.value =
                    Response(
                        responseType = Status.ERROR,
                        error = Error(error.message)
                    )
            }, {
                Log.d(TAG, "Complete!")
            })

        addDisposable(disposable)
    }

    fun getTransactionHistory() = transactionHistory

    companion object {
        val TAG = TransactionHistoryViewModel::class.java.simpleName
    }

}