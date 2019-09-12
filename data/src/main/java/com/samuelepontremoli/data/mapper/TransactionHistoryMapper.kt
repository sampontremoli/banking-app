package com.samuelepontremoli.data.mapper

import com.samuelepontremoli.data.network.dto.Transaction
import com.samuelepontremoli.data.network.dto.Account
import com.samuelepontremoli.data.db.entities.TransactionData
import com.samuelepontremoli.data.db.entities.TransactionHistoryData

class TransactionHistoryMapper: Mapper<TransactionHistoryData, Account>() {

    override fun mapFrom(from: TransactionHistoryData): Account =
        Account(
            account = from.accountData.accountId,
            balance = from.accountData.balance,
            transactions = mapTransactionsToPresentation(from.transactions)
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