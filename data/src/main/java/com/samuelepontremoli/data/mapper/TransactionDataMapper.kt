package com.samuelepontremoli.data.mapper

import com.samuelepontremoli.data.db.entities.TransactionDb
import com.samuelepontremoli.data.network.dto.AccountDTO
import com.samuelepontremoli.data.network.dto.TransactionDTO

class TransactionDataMapper : Mapper<AccountDTO, List<TransactionDb>>() {

    override fun mapFrom(from: AccountDTO): List<TransactionDb> =
        from.transactions.map { mapTransactionToDatabase(it, from) }

    private fun mapTransactionToDatabase(transactionDto: TransactionDTO, accountDto: AccountDTO): TransactionDb =
        TransactionDb(
            transactionId = transactionDto.id,
            accountId = accountDto.account,
            amount = transactionDto.amount,
            description = transactionDto.description,
            otherAccount = transactionDto.otherAccount,
            date = transactionDto.date
        )

}
