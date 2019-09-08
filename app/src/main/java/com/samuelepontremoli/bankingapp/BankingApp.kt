package com.samuelepontremoli.bankingapp

import android.app.Application
import com.samuelepontremoli.bankingapp.di.databaseModule
import com.samuelepontremoli.bankingapp.di.networkModule
import com.samuelepontremoli.bankingapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BankingApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        startKoin {
            androidContext(this@BankingApp)
            modules(listOf(networkModule, databaseModule, viewModelModule))
        }
    }

}