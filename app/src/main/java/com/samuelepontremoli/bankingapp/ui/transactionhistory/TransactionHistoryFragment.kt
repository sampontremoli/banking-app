package com.samuelepontremoli.bankingapp.ui.transactionhistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.samuelepontremoli.bankingapp.R
import com.samuelepontremoli.bankingapp.entities.Status
import kotlinx.android.synthetic.main.fragment_transaction_history.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TransactionHistoryFragment : Fragment() {

    private val transactionHistory: TransactionHistoryViewModel by viewModel()
    private lateinit var transactionHistoryAdapter: TransactionHistoryAdapter

    companion object {
        fun newInstance(): TransactionHistoryFragment {
            return TransactionHistoryFragment()
        }

        val TAG = TransactionHistoryFragment::class.java.simpleName
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_transaction_history, container, false)
        createRecyclerView(view)
        return view
    }

    override fun onStart() {
        super.onStart()
        transactionHistory.fetchData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**
        transactionHistory.getTransactionHistory().observe(this, Observer {
        when (it?.responseType) {
        Status.ERROR -> {
        //Error handling
        }
        Status.LOADING -> {
        //Progress
        }
        Status.SUCCESSFUL -> {
        // On Successful response
        }
        }
        it?.data?.let { response ->
        transactionHistoryAdapter.updateList(response.transactions)
        }
        })
         */
    }

    private fun createRecyclerView(view: View) {
        with(view) {
            transactionHistoryAdapter = TransactionHistoryAdapter()
            transactions_list.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            transactions_list.adapter = transactionHistoryAdapter
        }
    }

}