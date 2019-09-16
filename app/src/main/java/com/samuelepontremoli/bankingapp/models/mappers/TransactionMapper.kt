package com.samuelepontremoli.bankingapp.models.mappers

import com.samuelepontremoli.bankingapp.extensions.format
import com.samuelepontremoli.bankingapp.extensions.formatDateComplete
import com.samuelepontremoli.bankingapp.models.Transaction
import com.samuelepontremoli.data.db.entities.TransactionDb
import com.samuelepontremoli.data.mapper.Mapper

class TransactionMapper : Mapper<TransactionDb, Transaction>() {

    override fun mapFrom(from: TransactionDb): Transaction {
        return Transaction(
            id = from.transactionId,
            amount = from.amount,
            amountBeautified = from.amount.format(2),
            description = from.description,
            otherAccount = from.otherAccount,
            date = from.date,
            dateFormatted = from.date.formatDateComplete()
        )
    }

}