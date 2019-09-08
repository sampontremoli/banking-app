package com.samuelepontremoli.bankingapp.di

import com.samuelepontremoli.bankingapp.network.createNetworkClient
import com.samuelepontremoli.data.api.TransactionHistoryApi
import com.samuelepontremoli.data.repository.TransactionHistoryRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

private const val RETROFIT_INSTANCE = "Retrofit"
private const val TRANSACTION_API = "TransactionHistoryApi"
const val TRANSACTION_REPO = "TransactionHistoryApiRepository"

val networkModule = module {

    single(named(RETROFIT_INSTANCE)) { createNetworkClient("https://api.myjson.com/") }

    single(named(TRANSACTION_API)) { (get(named(RETROFIT_INSTANCE)) as Retrofit).create(TransactionHistoryApi::class.java) }

    single(named(TRANSACTION_REPO)) { TransactionHistoryRepository(get(named(TRANSACTION_API))) }

}