package com.samuelepontremoli.data.network.dto

import com.google.gson.annotations.SerializedName

data class TransactionDTO(
    @SerializedName("id")
    val id: String,
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("description")
    val description: String,
    @SerializedName("otherAccount")
    val otherAccount: String,
    @SerializedName("date")
    val date: String
)