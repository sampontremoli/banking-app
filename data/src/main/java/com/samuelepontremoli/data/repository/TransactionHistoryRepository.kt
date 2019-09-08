package com.samuelepontremoli.data.repository

import com.samuelepontremoli.data.api.TransactionHistoryApi
import com.samuelepontremoli.data.entities.TransactionHistoryData
import io.reactivex.Flowable

class TransactionHistoryRepository(private val api: TransactionHistoryApi) {

    fun getTransactionHistoryRemote(): Flowable<TransactionHistoryData> {
        return api.getTransactionHistory()
    }

}