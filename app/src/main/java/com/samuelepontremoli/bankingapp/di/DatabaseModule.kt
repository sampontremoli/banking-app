package com.samuelepontremoli.bankingapp.di

import com.samuelepontremoli.data.db.BankingDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    single {
        BankingDatabase.getDatabase(androidApplication())
    }

}