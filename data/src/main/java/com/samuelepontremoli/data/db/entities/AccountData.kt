package com.samuelepontremoli.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "account")
data class AccountData(

    @SerializedName("account")
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    val accountId: String,

    @SerializedName("balance")
    val balance: Double

)