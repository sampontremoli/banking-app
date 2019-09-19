package com.samuelepontremoli.data

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.samuelepontremoli.data.db.AccountDao
import com.samuelepontremoli.data.db.BankingDatabase
import com.samuelepontremoli.data.db.TransactionDao
import com.samuelepontremoli.data.db.entities.AccountDb
import com.samuelepontremoli.data.db.entities.TransactionDb
import org.junit.runner.RunWith
import java.io.IOException
import org.junit.*


@RunWith(AndroidJUnit4::class)
class TransactionDaoTest {

    private lateinit var db: BankingDatabase
    private lateinit var transactionDao: TransactionDao
    private lateinit var accountDao: AccountDao

    private val fakeAccount = AccountDb("NL30MOYO0001234567", 100.00)

    private val fakeTransaction1 = TransactionDb("trx1", "NL30MOYO0001234567",
        -18.20, "pizza", "NL18ABNA0484869868",
        "2018-05-14T14:19:00Z", 0.0, 0.0)

    private val fakeTransaction2 = TransactionDb("trx3", "NL30MOYO0001234567",
        -7.00, "albert heijn to go sandwich", "NL40ABNA0423660144",
        "2018-05-14T08:19:00Z", 0.0, 0.0)

    private val fakeTransaction3 = TransactionDb("trx2", "NL30MOYO0001234567",
        30.00, "thanks for the dinner", "NL91ABNA0417164300",
        "2018-05-14T12:19:00Z", 0.0, 0.0)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, BankingDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        transactionDao = db.transactionDao()
        accountDao = db.accountDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun checkIf_isEmptyIfNothingInserted() {
        transactionDao.getTransactionById(fakeTransaction1.transactionId)
            .test()
            .assertNoValues()
    }

    @Test
    @Throws(Exception::class)
    fun onInsertingTransaction_checkIf_transactionIsCorrect() {
        //When inserting a new transaction inside the db in relation to an account
        accountDao.insertAccount(fakeAccount)
        transactionDao.insertTransaction(fakeTransaction1)
        transactionDao.getTransactionById(fakeTransaction1.transactionId)
            .test()
            // assertValue asserts that there was only one emission of the transaction
            .assertValue {
                // The emitted transaction is the expected one
                return@assertValue fakeTransaction1.transactionId == it.transactionId
                        && fakeTransaction1.amount == it.amount
                        && fakeTransaction1.date == it.date
                        && fakeTransaction1.accountId == it.accountId
                        && fakeTransaction1.otherAccount == it.otherAccount
                        && fakeTransaction1.description == it.description
            }
    }

    @Test
    @Throws(InterruptedException::class)
    fun onInsertingTransactions_checkIf_RowCountIsCorrect() {
        accountDao.insertAccount(fakeAccount)
        val transactions = listOf<TransactionDb>(fakeTransaction1, fakeTransaction2, fakeTransaction3)
        transactions.forEach { transactionDao.insertTransaction(it) }
        accountDao.getAccount()
            .test()
            .assertValue {
                return@assertValue it.transactions.size == 3
            }
    }

}