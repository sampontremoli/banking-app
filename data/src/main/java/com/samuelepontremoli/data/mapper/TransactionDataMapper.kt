package com.samuelepontremoli.data.mapper

import com.samuelepontremoli.data.db.entities.TransactionData
import com.samuelepontremoli.data.network.dto.AccountDTO
import com.samuelepontremoli.data.network.dto.TransactionDTO

class TransactionDataMapper : Mapper<AccountDTO, List<TransactionData>>() {

    override fun mapFrom(from: AccountDTO): List<TransactionData> =
        from.transactions.map { mapTransactionToDatabase(it, from) }

    private fun mapTransactionToDatabase(transactionDto: TransactionDTO, accountDto: AccountDTO): TransactionData =
        TransactionData(
            transactionId = transactionDto.id,
            accountId = accountDto.account,
            amount = transactionDto.amount,
            description = transactionDto.description,
            otherAccount = transactionDto.otherAccount,
            date = transactionDto.date
        )

}
