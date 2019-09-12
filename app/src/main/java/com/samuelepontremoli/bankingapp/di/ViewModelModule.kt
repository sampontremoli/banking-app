package com.samuelepontremoli.bankingapp.di

import com.samuelepontremoli.data.presentation.AccountMapper
import com.samuelepontremoli.bankingapp.ui.transactiondetail.TransactionDetailViewModel
import com.samuelepontremoli.bankingapp.ui.transactionhistory.TransactionHistoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        TransactionHistoryViewModel(
            get(named(TRANSACTION_REPO)),
            mapper = AccountMapper()
        )
    }

    viewModel { TransactionDetailViewModel() }

}
