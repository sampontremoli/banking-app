package com.samuelepontremoli.bankingapp.entities

data class Transaction (
    val id : String,
    val amount : Double,
    val description : String,
    val otherAccount : String,
    val date : String
)