package com.samuelepontremoli.data.mapper.account

import com.samuelepontremoli.data.db.entities.AccountDb
import com.samuelepontremoli.data.mapper.Mapper
import com.samuelepontremoli.data.network.dto.AccountDTO

class AccountDbNetworkMapper : Mapper<AccountDb, AccountDTO>() {

    override fun mapFrom(from: AccountDb): AccountDTO {
        return AccountDTO(
            account = from.account,
            balance = from.balance,
            //TODO List?
            transactions = listOf()
        )
    }

}