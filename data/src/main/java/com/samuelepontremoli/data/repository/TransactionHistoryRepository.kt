package com.samuelepontremoli.data.repository

import com.samuelepontremoli.data.network.TransactionHistoryApi
import com.samuelepontremoli.data.network.dto.AccountDTO
import io.reactivex.Flowable

class TransactionHistoryRepository(private val api: TransactionHistoryApi) {

    fun getTransactionHistoryRemote(): Flowable<AccountDTO> {
        return api.getTransactionHistory()
    }

}