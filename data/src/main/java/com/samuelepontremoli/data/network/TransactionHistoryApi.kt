package com.samuelepontremoli.data.network

import com.samuelepontremoli.data.network.dto.AccountDTO
import io.reactivex.Flowable
import retrofit2.http.GET

interface TransactionHistoryApi {

    @GET("bins/1eku71")
    fun getTransactionHistory(): Flowable<AccountDTO>

}