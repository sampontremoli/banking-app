package com.samuelepontremoli.data.mapper.transaction

import com.samuelepontremoli.data.db.entities.TransactionDb
import com.samuelepontremoli.data.mapper.Mapper
import com.samuelepontremoli.data.network.dto.TransactionDTO

class TransactionDbNetworkMapper : Mapper<List<TransactionDb>, List<TransactionDTO>>() {

    override fun mapFrom(from: List<TransactionDb>): List<TransactionDTO> =
        from.map { mapDbTransactionToNetwork(it) }

    private fun mapDbTransactionToNetwork(transaction: TransactionDb): TransactionDTO =
        TransactionDTO(
            id = transaction.transactionId,
            amount = transaction.amount,
            description = transaction.description,
            otherAccount = transaction.otherAccount,
            date = transaction.date
        )

}