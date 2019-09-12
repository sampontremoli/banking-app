package com.samuelepontremoli.data.repository

import com.samuelepontremoli.data.db.entities.AccountData
import com.samuelepontremoli.data.network.TransactionHistoryApi
import com.samuelepontremoli.data.db.entities.TransactionHistoryData
import com.samuelepontremoli.data.network.entities.TransactionHistory
import io.reactivex.Flowable

class TransactionHistoryRepository(private val api: TransactionHistoryApi) {

    fun getTransactionHistoryRemote(): Flowable<AccountData> {
        return api.getTransactionHistory()
    }

}