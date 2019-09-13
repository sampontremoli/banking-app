package com.samuelepontremoli.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account")
data class AccountDb(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val account: String,

    @ColumnInfo(name = "balance") val balance: Double

)