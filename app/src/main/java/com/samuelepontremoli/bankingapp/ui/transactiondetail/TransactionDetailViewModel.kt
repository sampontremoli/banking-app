package com.samuelepontremoli.bankingapp.ui.transactiondetail

import androidx.lifecycle.MutableLiveData
import com.samuelepontremoli.bankingapp.common.BaseViewModel
import com.samuelepontremoli.data.network.Response
import com.samuelepontremoli.data.network.Status
import com.samuelepontremoli.bankingapp.models.Transaction
import com.samuelepontremoli.bankingapp.models.mappers.TransactionMapper
import com.samuelepontremoli.data.repository.TransactionRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TransactionDetailViewModel(
    private val repository: TransactionRepository,
    private val mapper: TransactionMapper
) : BaseViewModel() {

    var transactionId: String = ""

    var transactionDate: String = ""

    private var transaction = MutableLiveData<Response<Transaction>>()

    override fun fetchData() {
        val disposable = repository.getTransactionById(transactionId)
            .flatMap { mapper.Flowable(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                transaction.value = Response(
                    responseType = Status.SUCCESSFUL,
                    data = response
                )
            }, { error ->
                transaction.value = Response(
                    responseType = Status.ERROR,
                    error = Error(error.message)
                )
            }, {})

        addDisposable(disposable)

        //TODO COMPLETE
        val disposable2 = repository.getTransactionsUntilCurrent(transactionDate)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

            }, {

            })
    }

    fun getTransaction() = transaction

    companion object {
        val TAG = TransactionDetailViewModel::class.java.simpleName
    }

}