package com.samuelepontremoli.data.repository

import com.samuelepontremoli.data.db.TransactionDao
import com.samuelepontremoli.data.db.entities.TransactionDb
import io.reactivex.Flowable

class TransactionRepository private constructor(
    private val transactionDao: TransactionDao
) {

    fun getTransactionHistoryLocal(accountId: String): Flowable<TransactionDb> {
        return transactionDao.getTransactionsForAccountByDate(accountId)
    }

    suspend fun insertTransaction(transaction: TransactionDb) {
        transactionDao.insertTransaction(transaction)
    }

    companion object {

        @Volatile
        private var instance: TransactionRepository? = null

        fun getInstance(transactionDao: TransactionDao) =
            instance ?: synchronized(this) {
                instance ?: TransactionRepository(transactionDao).also { instance = it }
            }

    }

}