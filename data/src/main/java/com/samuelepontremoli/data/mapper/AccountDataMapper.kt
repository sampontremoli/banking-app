package com.samuelepontremoli.data.mapper

import com.samuelepontremoli.data.db.entities.AccountDb
import com.samuelepontremoli.data.network.dto.AccountDTO

class AccountDataMapper : Mapper<AccountDTO, AccountDb>() {

    override fun mapFrom(from: AccountDTO): AccountDb {
        return AccountDb(
            account = from.account,
            balance = from.balance
        )
    }

}