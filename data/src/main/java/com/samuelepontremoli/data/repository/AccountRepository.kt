package com.samuelepontremoli.data.repository

import com.samuelepontremoli.data.db.AccountDao
import com.samuelepontremoli.data.db.entities.AccountDb
import com.samuelepontremoli.data.network.AccountTransactionsApi
import com.samuelepontremoli.data.network.dto.AccountDTO
import io.reactivex.Flowable

class AccountRepository private constructor(
    private val api: AccountTransactionsApi,
    private val accountDao: AccountDao
) {

    fun getAccountRemote(): Flowable<AccountDTO> {
        return api.getAccountRemote()
    }

    fun getAccountLocal(accountId: String): Flowable<AccountDb> {
        return accountDao.getAccount(accountId)
    }

    suspend fun insertAccount(account: AccountDb) {
        return accountDao.insertAccount(account)
    }

    companion object {

        @Volatile
        private var instance: AccountRepository? = null

        fun getInstance(api: AccountTransactionsApi, accountDao: AccountDao) =
            instance ?: synchronized(this) {
                instance ?: AccountRepository(api, accountDao).also { instance = it }
            }

    }

}