package com.samuelepontremoli.bankingapp.ui.transactionhistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.samuelepontremoli.bankingapp.R
import com.samuelepontremoli.bankingapp.extensions.hide
import com.samuelepontremoli.bankingapp.extensions.show
import com.samuelepontremoli.bankingapp.models.Account
import com.samuelepontremoli.bankingapp.models.BaseItem
import com.samuelepontremoli.bankingapp.models.Transaction
import com.samuelepontremoli.bankingapp.ui.main.ItemClickListener
import com.samuelepontremoli.data.network.Status
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
                    loadingView?.hide()
                }
                Status.LOADING -> {
                    loadingView?.show()
                }
                Status.SUCCESSFUL -> {
                    it.data?.let { response ->
                        addDataSet(response)
                    }
                    loadingView?.hide()
                }
            }
        })
    }

    private fun addDataSet(response: Account) {
        val list = mutableListOf<BaseItem>()
        list.add(Account(response.account, response.balance, response.balanceFormatted, emptyList()))
        list.addAll(response.transactions)
        listAdapter.updateList(list)
    }

    private fun createRecyclerView(view: View) {
        listAdapter = TransactionHistoryAdapter(this)
        with(view) {
            val layoutManager = GridLayoutManager(context, COLUMNS)
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if(position == 0) {
                        COLUMNS
                    } else {
                        1
                    }
                }
            }
            transactions_list.layoutManager = layoutManager
            val spacingInPixels = resources.getDimensionPixelSize(R.dimen.list_spacing)
            transactions_list.addItemDecoration(GridItemDecoration(spacingInPixels))
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
        const val COLUMNS = 2
        val TAG = TransactionHistoryFragment::class.java.simpleName
    }

}