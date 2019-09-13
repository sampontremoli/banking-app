package com.samuelepontremoli.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.samuelepontremoli.data.db.entities.TransactionDb
import io.reactivex.Flowable

@Dao
interface TrasactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: TransactionDb)

    @Query("SELECT * FROM transactions WHERE account_id = :accountId ORDER BY date DESC")
    fun getTransactionsForAccountByDate(accountId: String): Flowable<TransactionDb>

}