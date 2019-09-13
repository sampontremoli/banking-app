package com.samuelepontremoli.bankingapp.di

import com.samuelepontremoli.data.db.BankingDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val DATABASE = "Database"

val databaseModule = module {

    single(named(DATABASE)) {
        BankingDatabase.getDatabase(androidApplication())
    }

}