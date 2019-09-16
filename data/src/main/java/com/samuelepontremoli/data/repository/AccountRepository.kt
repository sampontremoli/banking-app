package com.samuelepontremoli.data.repository

import com.samuelepontremoli.data.db.AccountDao
import com.samuelepontremoli.data.db.BankingDatabase
import com.samuelepontremoli.data.db.entities.AccountDb
import com.samuelepontremoli.data.db.entities.TransactionDb
import com.samuelepontremoli.data.mapper.account.AccountDbNetworkMapper
import com.samuelepontremoli.data.mapper.account.AccountNetworkDbMapper
import com.samuelepontremoli.data.mapper.transaction.TransactionNetworkDbMapper
import com.samuelepontremoli.data.network.AccountTransactionsApi
import com.samuelepontremoli.data.network.dto.AccountDTO
import io.reactivex.Flowable

class AccountRepository private constructor(
    private val api: AccountTransactionsApi,
    private val accountDao: AccountDao
) {

    //TODO INJECT
    private val accountDbNetworkMapper: AccountDbNetworkMapper = AccountDbNetworkMapper()
    private val accountNetworkDbMapper: AccountNetworkDbMapper = AccountNetworkDbMapper()
    private val transactionsNetworkDbMapper: TransactionNetworkDbMapper = TransactionNetworkDbMapper()

    private fun getAccountRemote(): Flowable<AccountDTO> {
        return api.getAccountRemote()
    }

    private fun getAccountLocal(): Flowable<AccountDb> {
        return accountDao.getAccount()
    }

    private fun insertAccount(account: AccountDb, transactions: List<TransactionDb>) {
        accountDao.insertAccount(account)
        accountDao.insertAll(transactions)
    }

    fun getAccount(): Flowable<AccountDTO> {
        val updatedAccount = getAccountRemote()
        return getAccountLocal().flatMap { accountDbNetworkMapper.Flowable(it) }
            .mergeWith(updatedAccount.doOnNext { remoteAccount ->
                insertAccount(
                    accountNetworkDbMapper.mapFrom(remoteAccount),
                    transactionsNetworkDbMapper.mapFrom(remoteAccount)
                )
            })
    }

    companion object {

        @Volatile
        private var instance: AccountRepository? = null

        fun getInstance(api: AccountTransactionsApi, database: BankingDatabase) =
            instance ?: synchronized(this) {
                instance ?: AccountRepository(api, database.accountDao()).also { instance = it }
            }

    }

}