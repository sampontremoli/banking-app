package com.samuelepontremoli.data.repository

import com.samuelepontremoli.data.db.BankingDatabase
import com.samuelepontremoli.data.db.TransactionDao
import com.samuelepontremoli.data.db.entities.TransactionDb
import io.reactivex.Flowable

class TransactionRepository private constructor(
    private val transactionDao: TransactionDao
) {

    fun getTransactionHistoryLocal(accountId: String): Flowable<TransactionDb> {
        return transactionDao.getTransactionsForAccountByDate(accountId)
    }

    fun insertTransaction(transaction: TransactionDb) {
        transactionDao.insertTransaction(transaction)
    }

    fun getTransactionById(transactionId: String): Flowable<TransactionDb> {
        return transactionDao.getTransactionById(transactionId)
    }

    companion object {

        @Volatile
        private var instance: TransactionRepository? = null

        fun getInstance(database: BankingDatabase) =
            instance ?: synchronized(this) {
                instance ?: TransactionRepository(database.transactionDao()).also { instance = it }
            }

    }

}