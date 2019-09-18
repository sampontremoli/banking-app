package com.samuelepontremoli.data.db

import androidx.room.*
import com.samuelepontremoli.data.db.entities.AccountDb
import com.samuelepontremoli.data.db.entities.AccountWithTransactionsDb
import com.samuelepontremoli.data.db.entities.TransactionDb
import io.reactivex.Flowable

@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAccount(account: AccountDb)

    @Insert
    fun insertAll(transactions: List<TransactionDb>)

    @Transaction
    @Query("SELECT * FROM account")
    fun getAccount(): Flowable<AccountWithTransactionsDb>

}