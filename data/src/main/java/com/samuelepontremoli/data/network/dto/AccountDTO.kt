package com.samuelepontremoli.data.network.dto

import com.google.gson.annotations.SerializedName
import com.samuelepontremoli.data.presentation.Transaction

data class AccountDTO(
    @SerializedName("account")
    val account: String,
    @SerializedName("balance")
    val balance: Double,
    @SerializedName("transactions")
    val transactions: List<TransactionDTO>
)