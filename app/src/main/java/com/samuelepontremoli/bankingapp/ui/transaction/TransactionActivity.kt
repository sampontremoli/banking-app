package com.samuelepontremoli.bankingapp.ui.transaction

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.samuelepontremoli.bankingapp.R

class TransactionActivity : AppCompatActivity() {

    companion object {
        val TAG = TransactionActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)
    }

}