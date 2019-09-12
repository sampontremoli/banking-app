package com.samuelepontremoli.data.mapper

import com.samuelepontremoli.data.network.dto.AccountDTO
import com.samuelepontremoli.data.network.dto.TransactionDTO
import com.samuelepontremoli.data.presentation.Account
import com.samuelepontremoli.data.presentation.Transaction

class AccountDTOMapper: Mapper<AccountDTO, Account>() {

    override fun mapFrom(from: AccountDTO): Account =
        Account(
            account = from.account,
            balance = from.balance,
            transactions = mapTransactionsToPresentation(from.transactions)
        )

    private fun mapTransactionsToPresentation(articles: List<TransactionDTO>?): List<Transaction> =
        articles?.map { mapTransactionToPresentation(it) } ?: emptyList()

    private fun mapTransactionToPresentation(transaction: TransactionDTO): Transaction =
        Transaction(
            id = transaction.id,
            amount = transaction.amount,
            description = transaction.description,
            otherAccount = transaction.otherAccount,
            date = transaction.date
        )

}