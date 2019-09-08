package com.samuelepontremoli.data.db.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.google.gson.annotations.SerializedName

data class TransactionHistoryData(

    @Embedded
    val accountData: AccountData,

    @SerializedName("transactions")
    @Relation(parentColumn = "id", entityColumn = "account_id", entity = TransactionData::class)
    val transactions: List<TransactionData> = emptyList()

)