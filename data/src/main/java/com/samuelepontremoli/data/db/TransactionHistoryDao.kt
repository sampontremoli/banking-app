package com.samuelepontremoli.data.db

import androidx.room.*
import com.samuelepontremoli.data.entities.AccountData
import com.samuelepontremoli.data.entities.TransactionHistoryData
import io.reactivex.Flowable

@Dao
interface TransactionHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccountData(accountData: AccountData)

    @Transaction
    @Query("SELECT * FROM account WHERE id = :accountId")
    fun getTransactionHistory(accountId: String): Flowable<TransactionHistoryData>

    @Query("SELECT * FROM `transaction` WHERE id = :id")
    fun getTransactionById(id: String): Flowable<Transaction>

    @Query("DELETE FROM account")
    suspend fun deleteAll()

}