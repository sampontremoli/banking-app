package com.samuelepontremoli.data.repository

import com.samuelepontremoli.data.db.BankingDatabase
import com.samuelepontremoli.data.db.TransactionDao
import com.samuelepontremoli.data.db.entities.TransactionDb
import io.reactivex.Flowable

class TransactionRepository private constructor(
    private val transactionDao: TransactionDao
) {

    fun getTransactionById(transactionId: String): Flowable<TransactionDb> {
        return transactionDao.getTransactionById(transactionId)
    }

    fun getTransactionsUntilCurrent(date: String): Flowable<List<TransactionDb>> {
        return transactionDao.getTransactionsUntilCurrent(date)
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