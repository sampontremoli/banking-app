package com.samuelepontremoli.bankingapp.models

data class Account(
    val account: String,
    val balance: Double,
    val balanceFormatted: String,
    val transactions: List<Transaction>
) : BaseItem()