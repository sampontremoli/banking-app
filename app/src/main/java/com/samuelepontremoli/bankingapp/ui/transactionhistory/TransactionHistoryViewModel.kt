package com.samuelepontremoli.bankingapp.ui.transactionhistory

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.samuelepontremoli.bankingapp.common.BaseViewModel
import com.samuelepontremoli.data.network.entities.Response
import com.samuelepontremoli.data.network.entities.Status
import com.samuelepontremoli.data.network.entities.TransactionHistory
import com.samuelepontremoli.data.network.entities.mapper.TransactionHistoryMapper
import com.samuelepontremoli.data.repository.TransactionHistoryRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TransactionHistoryViewModel(
    private val repository: TransactionHistoryRepository,
    private val mapper: TransactionHistoryMapper
) : BaseViewModel() {

    private var transactionHistory = MutableLiveData<Response<TransactionHistory>>()

    override fun fetchData() {
        val disposable = repository.getTransactionHistoryRemote()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                Log.d(TAG, "Success!")
                transactionHistory.value =
                    Response(responseType = Status.SUCCESSFUL, data = response)
            }, { error ->
                Log.d(TAG, "Error!")
                error.printStackTrace()
                transactionHistory.value =
                    Response(responseType = Status.ERROR, error = Error(error.message))
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