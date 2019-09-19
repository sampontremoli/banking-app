package com.samuelepontremoli.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.samuelepontremoli.data.db.BankingDatabase.Companion.DB_VERSION
import com.samuelepontremoli.data.db.entities.AccountDb
import com.samuelepontremoli.data.db.entities.TransactionDb

/**
 * Room Database of the application
 */
@Database(entities = [AccountDb::class, TransactionDb::class], version = DB_VERSION, exportSchema = true)
abstract class BankingDatabase : RoomDatabase() {

    abstract fun accountDao(): AccountDao
    abstract fun transactionDao(): TransactionDao

    companion object {
        const val DB_VERSION = 1
        private const val DB_NAME = "banking_database.db"

        @Volatile private var instance: BankingDatabase? = null

        /**
         * Returns instance if not null, otherwise creates the db in a thread-safe way and returns it
         */
        fun getDatabase(context: Context): BankingDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): BankingDatabase {
            return Room.databaseBuilder(context.applicationContext, BankingDatabase::class.java, DB_NAME)
                .addMigrations(MIGRATION_1_TO_2)
                .build()
        }

        private val MIGRATION_1_TO_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) = Unit
        }

    }

}