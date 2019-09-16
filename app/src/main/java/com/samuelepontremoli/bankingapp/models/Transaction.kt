package com.samuelepontremoli.bankingapp.models

data class Transaction(
    val id: String,
    val amount: Double,
    val description: String,
    val otherAccount: String,
    val date: String
)