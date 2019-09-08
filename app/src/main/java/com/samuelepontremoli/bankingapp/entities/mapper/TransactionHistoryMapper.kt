package com.samuelepontremoli.bankingapp.entities.mapper

import com.samuelepontremoli.bankingapp.entities.Transaction
import com.samuelepontremoli.bankingapp.entities.TransactionHistory
import com.samuelepontremoli.data.entities.TransactionData
import com.samuelepontremoli.data.entities.TransactionHistoryData

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