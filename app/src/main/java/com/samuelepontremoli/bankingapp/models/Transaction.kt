package com.samuelepontremoli.bankingapp.models

data class Transaction(
    val id: String,
    val amount: Double,
    val amountBeautified: String,
    val description: String,
    val otherAccount: String,
    val date: String,
    val dateFormatted: String
)