package com.samuelepontremoli.bankingapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.samuelepontremoli.bankingapp.R
import com.samuelepontremoli.bankingapp.ui.transactionhistory.TransactionHistoryFragment

class TransactionActivity : AppCompatActivity() {

    companion object {
        val TAG = TransactionActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

        if (savedInstanceState == null) {
            initTransactionHistoryFragment()
        }

    }

    private fun initTransactionHistoryFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(
                R.id.fragment_container,
                TransactionHistoryFragment.newInstance(),
                TransactionHistoryFragment.TAG
            )
            .commit()
    }

}