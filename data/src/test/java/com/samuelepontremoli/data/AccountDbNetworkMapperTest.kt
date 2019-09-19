package com.samuelepontremoli.data

import com.samuelepontremoli.data.db.entities.AccountDb
import com.samuelepontremoli.data.db.entities.AccountWithTransactionsDb
import com.samuelepontremoli.data.db.entities.TransactionDb
import com.samuelepontremoli.data.mapper.account.AccountDbNetworkMapper
import com.samuelepontremoli.data.network.dto.AccountDTO
import com.samuelepontremoli.data.network.dto.TransactionDTO
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert
import org.junit.Test

class AccountDbNetworkMapperTest {

    private val FAKE_ACCOUNT_ID: String = "FAKE_ID"
    private val FAKE_ACCOUNT_BALANCE: Double = 100.00

    private var accountDbNetworkMapper = AccountDbNetworkMapper()

    private val fakeTransaction1 = TransactionDb(
        "trx1", "NL30MOYO0001234567",
        -18.20, "pizza", "NL18ABNA0484869868",
        "2018-05-14T14:19:00Z", 0.0, 0.0
    )

    private val fakeTransaction2 = TransactionDb(
        "trx3", "NL30MOYO0001234567",
        -7.00, "albert heijn to go sandwich", "NL40ABNA0423660144",
        "2018-05-14T08:19:00Z", 0.0, 0.0
    )

    private val fakeTransaction3 = TransactionDb(
        "trx2", "NL30MOYO0001234567",
        30.00, "thanks for the dinner", "NL91ABNA0417164300",
        "2018-05-14T12:19:00Z", 0.0, 0.0
    )

    @Test
    fun testMapAccount() {
        val accountDb = createFakeAccount()
        val accountDto = accountDbNetworkMapper.mapFrom(accountDb)

        Assert.assertThat(accountDto, instanceOf(AccountDTO::class.java))
        Assert.assertThat(accountDto.account, `is`(FAKE_ACCOUNT_ID))
        Assert.assertThat(accountDto.balance, `is`(FAKE_ACCOUNT_BALANCE))
    }

    @Test
    fun testMapAccountWithTransactions() {
        val accountDb = createFakeAccountWithTransactions()
        val accountDto = accountDbNetworkMapper.mapFrom(accountDb)

        Assert.assertThat(accountDto.transactions[0], instanceOf(TransactionDTO::class.java))
        Assert.assertThat(accountDto.transactions[1], instanceOf(TransactionDTO::class.java))
        Assert.assertThat(accountDto.transactions[2], instanceOf(TransactionDTO::class.java))
        Assert.assertThat(accountDto.transactions.size, `is`(3))
    }

    private fun createFakeAccount(): AccountWithTransactionsDb {
        return AccountWithTransactionsDb(
            AccountDb(FAKE_ACCOUNT_ID, FAKE_ACCOUNT_BALANCE),
            emptyList()
        )
    }

    private fun createFakeAccountWithTransactions(): AccountWithTransactionsDb {
        return AccountWithTransactionsDb(
            AccountDb(FAKE_ACCOUNT_ID, FAKE_ACCOUNT_BALANCE),
            listOf(fakeTransaction1, fakeTransaction2, fakeTransaction3)
        )
    }

}