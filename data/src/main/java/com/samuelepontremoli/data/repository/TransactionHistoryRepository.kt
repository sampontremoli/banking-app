package com.samuelepontremoli.data.repository

import com.samuelepontremoli.data.network.TransactionHistoryApi
import com.samuelepontremoli.data.network.dto.Account
import io.reactivex.Flowable

class TransactionHistoryRepository(private val api: TransactionHistoryApi) {

    fun getTransactionHistoryRemote(): Flowable<Account> {
        return api.getTransactionHistory()
    }

}