package com.samuelepontremoli.bankingapp.entities

data class TransactionHistory (
    val account : String,
    val balance : Double,
    val transactions : List<Transaction>
)