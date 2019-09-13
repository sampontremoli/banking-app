package com.samuelepontremoli.data.db.entities

import androidx.room.Embedded
import androidx.room.Relation

/**
 * This class captures the relationship between a [TransactionDb] and a user's [AccountDb], which is
 * used by Room to fetch the related entities.
 */
data class AccountWithTransactionsDb(

    @Embedded
    val account: AccountDb,

    @Relation(parentColumn = "id", entityColumn = "account_id")
    val transactions: List<TransactionDb> = emptyList()

)