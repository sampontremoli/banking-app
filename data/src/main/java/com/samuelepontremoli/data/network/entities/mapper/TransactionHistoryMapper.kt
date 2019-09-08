package com.samuelepontremoli.data.network.entities.mapper

import com.samuelepontremoli.data.network.entities.Transaction
import com.samuelepontremoli.data.network.entities.TransactionHistory
import com.samuelepontremoli.data.db.entities.TransactionData
import com.samuelepontremoli.data.db.entities.TransactionHistoryData

class TransactionHistoryMapper: Mapper<TransactionHistoryData, TransactionHistory>() {

    override fun mapFrom(transactionData: TransactionHistoryData): TransactionHistory =
        TransactionHistory(
            account = transactionData.accountData.accountId,
            balance = transactionData.accountData.balance,
            transactions = mapTransactionsToPresentation(transactionData.transactions)
        )

    private fun mapTransactionsToPresentation(articles: List<TransactionData>?): List<Transaction> =
        articles?.map { mapTransactionToPresentation(it) } ?: emptyList()

    private fun mapTransactionToPresentation(transaction: TransactionData): Transaction =
        Transaction(
            id = transaction.transactionId,
            amount = transaction.amount,
            description = transaction.description,
            otherAccount = transaction.otherAccount,
            date = transaction.date
        )

}