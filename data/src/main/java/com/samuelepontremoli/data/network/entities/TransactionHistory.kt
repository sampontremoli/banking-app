package com.samuelepontremoli.data.network.entities

data class TransactionHistory (
    val account : String,
    val balance : Double,
    val transactions : List<Transaction>
)