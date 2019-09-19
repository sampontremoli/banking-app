package com.samuelepontremoli.data

import com.samuelepontremoli.data.db.entities.TransactionDb
import com.samuelepontremoli.data.mapper.transaction.TransactionNetworkDbMapper
import com.samuelepontremoli.data.network.dto.AccountDTO
import com.samuelepontremoli.data.network.dto.TransactionDTO
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test

class TransactionNetworkDbMapperTest {

    private val FAKE_ACCOUNT_ID: String = "FAKE_ID"
    private val FAKE_ACCOUNT_BALANCE: Double = 100.00

    private val fakeTransaction1 = TransactionDTO(
        "trx1",
        -18.20, "pizza", "NL18ABNA0484869868",
        "2018-05-14T14:19:00Z"
    )

    private val fakeTransaction2 = TransactionDTO(
        "trx3",
        -7.00, "albert heijn to go sandwich", "NL40ABNA0423660144",
        "2018-05-14T08:19:00Z"
    )

    private val fakeTransaction3 = TransactionDTO(
        "trx2",
        30.00, "thanks for the dinner", "NL91ABNA0417164300",
        "2018-05-14T12:19:00Z"
    )

    private var transactionNetworkDbMapper = TransactionNetworkDbMapper()

    @Test
    fun testMapAccount() {
        val accountDto = createFakeAccount()
        val transactions = transactionNetworkDbMapper.mapFrom(accountDto)

        Assert.assertThat(transactions[0], CoreMatchers.instanceOf(TransactionDb::class.java))
        Assert.assertThat(transactions.size, CoreMatchers.`is`(3))
    }

    private fun createFakeAccount(): AccountDTO {
        return AccountDTO(
            FAKE_ACCOUNT_ID,
            FAKE_ACCOUNT_BALANCE,
            listOf(fakeTransaction1, fakeTransaction2, fakeTransaction3)
        )
    }

}