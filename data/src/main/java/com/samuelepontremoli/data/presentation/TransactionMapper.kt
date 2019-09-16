package com.samuelepontremoli.data.presentation

import com.samuelepontremoli.data.db.entities.TransactionDb
import com.samuelepontremoli.data.mapper.Mapper

class TransactionMapper : Mapper<TransactionDb, Transaction>() {

    override fun mapFrom(from: TransactionDb): Transaction {
        return Transaction(
            id = from.transactionId,
            amount = from.amount,
            description = from.description,
            otherAccount = from.otherAccount,
            date = from.date
        )
    }

}