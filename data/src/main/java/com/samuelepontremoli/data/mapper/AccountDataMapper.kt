package com.samuelepontremoli.data.mapper

import com.samuelepontremoli.data.db.entities.AccountData
import com.samuelepontremoli.data.network.dto.AccountDTO
import com.samuelepontremoli.data.network.dto.TransactionDTO
import com.samuelepontremoli.data.presentation.Account
import com.samuelepontremoli.data.presentation.Transaction

class AccountDataMapper : Mapper<AccountDTO, AccountData>() {

    override fun mapFrom(from: AccountDTO): AccountData {
        return AccountData(
            account = from.account,
            balance = from.balance
        )
    }

}