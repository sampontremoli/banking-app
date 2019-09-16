package com.samuelepontremoli.data.mapper.account

import com.samuelepontremoli.data.db.entities.AccountDb
import com.samuelepontremoli.data.mapper.Mapper
import com.samuelepontremoli.data.network.dto.AccountDTO

class AccountNetworkDbMapper : Mapper<AccountDTO, AccountDb>() {

    override fun mapFrom(from: AccountDTO): AccountDb {
        return AccountDb(
            account = from.account,
            balance = from.balance
        )
    }

}