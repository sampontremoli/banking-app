package com.samuelepontremoli.data.network

import com.samuelepontremoli.data.network.dto.AccountDTO
import io.reactivex.Flowable
import retrofit2.http.GET

interface AccountTransactionsApi {

    @GET("bins/1eku71")
    fun getAccountRemote(): Flowable<AccountDTO>

}