package com.samuelepontremoli.data.mapper.account

import com.samuelepontremoli.data.db.entities.AccountDb
import com.samuelepontremoli.data.db.entities.AccountWithTransactionsDb
import com.samuelepontremoli.data.db.entities.TransactionDb
import com.samuelepontremoli.data.mapper.Mapper
import com.samuelepontremoli.data.network.dto.AccountDTO
import com.samuelepontremoli.data.network.dto.TransactionDTO

class AccountDbNetworkMapper : Mapper<AccountWithTransactionsDb, AccountDTO>() {

    override fun mapFrom(from: AccountWithTransactionsDb): AccountDTO {
        return AccountDTO(
            account = from.account.account,
            balance = from.account.balance,
            transactions = from.transactions.map { mapDbTransactionToNetwork(it) }
        )
    }

    private fun mapDbTransactionToNetwork(transaction: TransactionDb): TransactionDTO {
        val transactionDto = TransactionDTO(
            transaction.transactionId,
            transaction.amount,
            transaction.description,
            transaction.otherAccount,
            transaction.date
        )
        transactionDto.balanceBefore = transaction.balanceBefore
        transactionDto.balanceAfter = transaction.balanceAfter
        return transactionDto
    }

}