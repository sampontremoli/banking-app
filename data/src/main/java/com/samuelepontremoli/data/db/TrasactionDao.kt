package com.samuelepontremoli.data.db

import androidx.room.Dao
import androidx.room.Query
import com.samuelepontremoli.data.db.entities.TransactionDb
import io.reactivex.Flowable

@Dao
interface TransactionDao {

    @Query("SELECT * FROM transactions WHERE date <= :date ORDER BY date ASC")
    fun getTransactionsUntilCurrent(date: String): Flowable<List<TransactionDb>>

    @Query("SELECT * FROM transactions WHERE id = :transactionId")
    fun getTransactionById(transactionId: String): Flowable<TransactionDb>

}