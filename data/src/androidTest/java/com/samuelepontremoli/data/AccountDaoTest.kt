package com.samuelepontremoli.data

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.samuelepontremoli.data.db.AccountDao
import com.samuelepontremoli.data.db.BankingDatabase
import com.samuelepontremoli.data.db.entities.AccountDb
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AccountDaoTest {

    private lateinit var db: BankingDatabase
    private lateinit var accountDao: AccountDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val fakeAccount = AccountDb("NL30MOYO0001234567", 10.00)

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, BankingDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        accountDao = db.accountDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun checkIf_isEmptyIfNothingInserted() {
        accountDao.getAccount()
            .test()
            .assertNoValues()
    }

    @Test
    @Throws(Exception::class)
    fun onInsertingAccount_checkIf_accountIsCorrect() {
        //When inserting a new account inside the db
        accountDao.insertAccount(fakeAccount)
        accountDao.getAccount()
            .test()
            // assertValue asserts that there was only one emission of the account
            .assertValue {
                // The emitted account is the expected one
                return@assertValue fakeAccount.account == it.account.account
                        && fakeAccount.balance == it.account.balance
            }
    }

    @Test
    @Throws(Exception::class)
    fun onUpdatingAccount_checkIf_accountIsUpdated() {
        //Given that we have an account inside the db
        accountDao.insertAccount(fakeAccount)
        // When we are updating the balance of the account
        val updatedAccount = AccountDb(fakeAccount.account, 40.00)
        accountDao.insertAccount(updatedAccount)
        accountDao.getAccount()
            .test()
            // assertValue asserts that there was only one emission of the account
            .assertValue {
                // The emitted account is the expected one
                return@assertValue it.account.account == fakeAccount.account
                        && it.account.balance == updatedAccount.balance
            }
    }

    @Test
    @Throws(Exception::class)
    fun onDeletingAccount_checkIf_accountTableIsEmpty() {
        //Given that we have an account inside the db
        accountDao.insertAccount(fakeAccount)
        //When we are deleting all accounts
        accountDao.deleteAllFromAccount()
        // When subscribing to the emissions of the account
        accountDao.getAccount()
            .test()
            // check that there's no user emitted
            .assertNoValues()
    }

}