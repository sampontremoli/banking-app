package com.samuelepontremoli.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "transactions",
    foreignKeys = [
        ForeignKey(
            entity = AccountDb::class,
            parentColumns = ["id"],
            childColumns = ["account_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class TransactionDb(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val transactionId: String,

    @ColumnInfo(name = "account_id") val accountId: String,

    @ColumnInfo(name = "amount") val amount: Double,

    @ColumnInfo(name = "description") val description: String,

    @ColumnInfo(name = "other_account") val otherAccount: String,

    @ColumnInfo(name = "date") val date: String

)