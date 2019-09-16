package com.samuelepontremoli.bankingapp.models.mappers

import com.samuelepontremoli.bankingapp.models.Account
import com.samuelepontremoli.bankingapp.models.Transaction
import com.samuelepontremoli.data.mapper.Mapper
import com.samuelepontremoli.data.network.dto.AccountDTO
import com.samuelepontremoli.data.network.dto.TransactionDTO

class AccountMapper: Mapper<AccountDTO, Account>() {

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