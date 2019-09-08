package com.samuelepontremoli.data.api

import com.samuelepontremoli.data.entities.TransactionHistoryData
import io.reactivex.Flowable
import retrofit2.http.GET

interface TransactionHistoryApi {

    @GET("bins/1eku71")
    fun getTransactionHistory(): Flowable<TransactionHistoryData>

}