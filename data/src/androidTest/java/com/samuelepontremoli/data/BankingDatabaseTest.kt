package com.samuelepontremoli.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.samuelepontremoli.data.db.AccountDao
import com.samuelepontremoli.data.db.BankingDatabase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class BankingDatabaseTest {

    private lateinit var db: BankingDatabase
    private lateinit var accountDao: AccountDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, BankingDatabase::class.java).build()
        accountDao = db.accountDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
//        accountDao.insertAll()
//        accountDao.insertAccount()
//        accountDao.getAccount()
    }

}