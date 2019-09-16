package com.samuelepontremoli.data.repository

import com.samuelepontremoli.data.db.AccountDao
import com.samuelepontremoli.data.db.BankingDatabase
import com.samuelepontremoli.data.db.entities.AccountDb
import com.samuelepontremoli.data.mapper.account.AccountDbNetworkMapper
import com.samuelepontremoli.data.mapper.account.AccountNetworkDbMapper
import com.samuelepontremoli.data.network.AccountTransactionsApi
import com.samuelepontremoli.data.network.dto.AccountDTO
import io.reactivex.Flowable

class AccountRepository private constructor(
    private val api: AccountTransactionsApi,
    database: BankingDatabase
) {

    private val accountDao: AccountDao = database.accountDao()

    //TODO INJECT
    private val accountDbNetworkMapper: AccountDbNetworkMapper = AccountDbNetworkMapper()
    private val accountNetworkDbMapper: AccountNetworkDbMapper = AccountNetworkDbMapper()

    private fun getAccountRemote(): Flowable<AccountDTO> {
        return api.getAccountRemote()
    }

    private fun getAccountLocal(): Flowable<AccountDb> {
        return accountDao.getAccount()
    }

    private fun insertAccount(account: AccountDb) {
        return accountDao.insertAccount(account)
    }

    fun getAccount(): Flowable<AccountDTO> {
        val updatedAccount = getAccountRemote()
        return getAccountLocal().flatMap { accountDbNetworkMapper.Flowable(it) }
            .mergeWith(updatedAccount.doOnNext { remoteAccount ->
                insertAccount(accountNetworkDbMapper.mapFrom(remoteAccount))
            })
    }

    companion object {

        @Volatile
        private var instance: AccountRepository? = null

        fun getInstance(api: AccountTransactionsApi, database: BankingDatabase) =
            instance ?: synchronized(this) {
                instance ?: AccountRepository(api, database).also { instance = it }
            }

    }

}