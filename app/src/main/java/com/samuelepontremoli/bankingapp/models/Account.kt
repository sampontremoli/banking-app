package com.samuelepontremoli.bankingapp.models

data class Account(
    val account: String,
    val balance: Double,
    val transactions: List<Transaction>
)