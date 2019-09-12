package com.samuelepontremoli.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "account")
data class AccountData(

    @SerializedName("account")
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    val account: String,

    @SerializedName("balance")
    val balance: Double

//    @SerializedName("transactions")
//    @Ignore
//    val transactions: List<TransactionData>

)