package com.samuelepontremoli.data.presentation

data class Account(
    val account: String,
    val balance: Double,
    val transactions: List<Transaction>
)