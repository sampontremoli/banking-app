package com.samuelepontremoli.data

import com.samuelepontremoli.data.db.entities.AccountDb
import com.samuelepontremoli.data.mapper.account.AccountNetworkDbMapper
import com.samuelepontremoli.data.network.dto.AccountDTO
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test

class AccountNetworkDbMapperTest {

    private val FAKE_ACCOUNT_ID: String = "FAKE_ID"
    private val FAKE_ACCOUNT_BALANCE: Double = 100.00

    private var accountNetworkDbMapper = AccountNetworkDbMapper()

    @Test
    fun testMapAccount() {
        val accountDto = createFakeAccount()
        val accountDb = accountNetworkDbMapper.mapFrom(accountDto)

        Assert.assertThat(accountDb, CoreMatchers.instanceOf(AccountDb::class.java))
        Assert.assertThat(accountDb.account, CoreMatchers.`is`(FAKE_ACCOUNT_ID))
        Assert.assertThat(accountDb.balance, CoreMatchers.`is`(FAKE_ACCOUNT_BALANCE))
    }

    private fun createFakeAccount() = AccountDTO(FAKE_ACCOUNT_ID, FAKE_ACCOUNT_BALANCE, emptyList())

}