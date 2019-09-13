package com.samuelepontremoli.bankingapp.di

import com.samuelepontremoli.data.db.BankingDatabase
import com.samuelepontremoli.data.network.createNetworkClient
import com.samuelepontremoli.data.network.AccountTransactionsApi
import com.samuelepontremoli.data.repository.AccountRepository
import com.samuelepontremoli.data.repository.TransactionRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

private const val RETROFIT_INSTANCE = "Retrofit"
private const val ACCOUNT_TRANSACTION_API = "AccountTransactionsApi"
const val ACCOUNT_REPO = "AccountRepository"
const val TRANSACTION_REPO = "TransactionRepository"

val networkModule = module {

    single(named(RETROFIT_INSTANCE)) {
        createNetworkClient("https://api.myjson.com/")
    }

    single(named(ACCOUNT_TRANSACTION_API)) {
        (get(named(RETROFIT_INSTANCE)) as Retrofit).create(
            AccountTransactionsApi::class.java
        )
    }

    single(named(ACCOUNT_REPO)) {
        AccountRepository.getInstance(
            get(named(ACCOUNT_TRANSACTION_API)),
            (get(named(DATABASE)) as BankingDatabase).accountDao()
        )
    }

    single(named(TRANSACTION_REPO)) {
        TransactionRepository.getInstance((get(named(DATABASE)) as BankingDatabase).transactionDao())
    }

}