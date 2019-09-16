package com.samuelepontremoli.bankingapp.ui.transactionhistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.samuelepontremoli.bankingapp.R
import com.samuelepontremoli.bankingapp.extensions.hide
import com.samuelepontremoli.bankingapp.extensions.show
import com.samuelepontremoli.bankingapp.ui.main.ItemClickListener
import com.samuelepontremoli.data.network.Status
import com.samuelepontremoli.bankingapp.models.Transaction
import kotlinx.android.synthetic.main.fragment_transaction_history.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TransactionHistoryFragment : Fragment(), ItemClickListener {

    private val viewModel: TransactionHistoryViewModel by viewModel()

    private lateinit var listAdapter: TransactionHistoryAdapter

    private var loadingView: FrameLayout? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_transaction_history, container, false)
        createRecyclerView(view)
        loadingView = view.findViewById(R.id.loading_view)
        return view
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTransactionHistory().observe(this, Observer {
            when (it?.responseType) {
                Status.ERROR -> {
                    //Error handling
                    loadingView?.hide()
                }
                Status.LOADING -> {
                    //Progress
                    loadingView?.show()
                }
                Status.SUCCESSFUL -> {
                    // On Successful response
                    it.data?.let { response ->
                        listAdapter.updateList(response.transactions)
                    }
                    loadingView?.hide()
                }
            }

        })
    }

    private fun createRecyclerView(view: View) {
        listAdapter = TransactionHistoryAdapter(this)
        with(view) {
            transactions_list.layoutManager =
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            transactions_list.adapter = listAdapter
        }
    }

    override fun itemClicked(transaction: Transaction) {
        val actionDetail =
            TransactionHistoryFragmentDirections.actionTransactionHistoryToTransactionDetail(
                transaction.id
            )
        findNavController().navigate(actionDetail)
    }

    companion object {
        val TAG = TransactionHistoryFragment::class.java.simpleName
    }

}