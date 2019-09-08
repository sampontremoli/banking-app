package com.samuelepontremoli.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "transaction",
    foreignKeys = [
        ForeignKey(
            entity = AccountData::class,
            parentColumns = ["id"],
            childColumns = ["account_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class TransactionData(

    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val transactionId: String,

    @ColumnInfo(name = "account_id")
    val accountId: String,

    @SerializedName("amount")
    val amount: Double,

    @SerializedName("description")
    val description: String,

    @SerializedName("otherAccount")
    @ColumnInfo(name = "other_account")
    val otherAccount: String,

    @SerializedName("date")
    val date: String

)