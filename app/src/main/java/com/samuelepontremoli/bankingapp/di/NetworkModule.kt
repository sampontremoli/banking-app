package com.samuelepontremoli.bankingapp.di

import com.samuelepontremoli.bankingapp.BuildConfig
import com.samuelepontremoli.data.mapper.account.AccountDbNetworkMapper
import com.samuelepontremoli.data.mapper.account.AccountNetworkDbMapper
import com.samuelepontremoli.data.mapper.transaction.TransactionNetworkDbMapper
import com.samuelepontremoli.data.network.AccountTransactionsApi
import com.samuelepontremoli.data.network.createNetworkClient
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
        createNetworkClient(BuildConfig.SERVER_URL)
    }

    single(named(ACCOUNT_TRANSACTION_API)) {
        (get(named(RETROFIT_INSTANCE)) as Retrofit).create(
            AccountTransactionsApi::class.java
        )
    }

    single(named(ACCOUNT_REPO)) {
        AccountRepository.getInstance(
            get(named(ACCOUNT_TRANSACTION_API)),
            get(named(DATABASE)),
            accountDbNetworkMapper = AccountDbNetworkMapper(),
            accountNetworkDbMapper = AccountNetworkDbMapper(),
            transactionsNetworkDbMapper = TransactionNetworkDbMapper()
        )
    }

    single(named(TRANSACTION_REPO)) {
        TransactionRepository.getInstance(get(named(DATABASE)))
    }

}