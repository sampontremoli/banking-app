package com.samuelepontremoli.data.mapper.transaction

import com.samuelepontremoli.data.db.entities.TransactionDb
import com.samuelepontremoli.data.mapper.Mapper
import com.samuelepontremoli.data.network.dto.AccountDTO
import com.samuelepontremoli.data.network.dto.TransactionDTO

class TransactionNetworkDbMapper : Mapper<AccountDTO, List<TransactionDb>>() {

    override fun mapFrom(from: AccountDTO): List<TransactionDb> =
        from.transactions.map { mapNetworkTransactionToDb(it, from) }

    private fun mapNetworkTransactionToDb(transactionDto: TransactionDTO, accountDto: AccountDTO): TransactionDb =
        TransactionDb(
            transactionId = transactionDto.id,
            accountId = accountDto.account,
            amount = transactionDto.amount,
            description = transactionDto.description,
            otherAccount = transactionDto.otherAccount,
            date = transactionDto.date
        )

}