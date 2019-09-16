package com.samuelepontremoli.data.db

import androidx.room.*
import com.samuelepontremoli.data.db.entities.AccountDb
import io.reactivex.Flowable

@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAccount(account: AccountDb)

    @Query("SELECT * FROM account LIMIT 1")
    fun getAccount(): Flowable<AccountDb>

}