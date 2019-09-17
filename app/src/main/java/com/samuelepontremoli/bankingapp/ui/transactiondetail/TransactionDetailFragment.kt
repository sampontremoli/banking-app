package com.samuelepontremoli.bankingapp.ui.transactiondetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.samuelepontremoli.bankingapp.R
import com.samuelepontremoli.bankingapp.models.Transaction
import com.samuelepontremoli.data.network.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class TransactionDetailFragment : Fragment() {

    private lateinit var transactionIdText: TextView
    private lateinit var transactionAmountText: TextView
    private lateinit var transactionDateText: TextView
    private lateinit var transactionDescriptionText: TextView
    private lateinit var transactionOtherAccountText: TextView
    private lateinit var balanceBeforeText: TextView
    private lateinit var balanceAfterText: TextView

    //TODO CONSIDER MOVING TO A METHOD
    private val transactionId: String? by lazy {
        val safeArgs = arguments?.let { TransactionDetailFragmentArgs.fromBundle(it) }
        safeArgs?.transactionId
    }

    //TODO CONSIDER MOVING TO A METHOD
    private val transactionDate: String? by lazy {
        val safeArgs = arguments?.let { TransactionDetailFragmentArgs.fromBundle(it) }
        safeArgs?.transactionDate
    }

    private val viewModel: TransactionDetailViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_transaction_detail, container, false)
        transactionIdText = view.findViewById(R.id.transaction_id_text)
        transactionAmountText = view.findViewById(R.id.transaction_amount_text)
        transactionDateText = view.findViewById(R.id.transaction_date_text)
        transactionDescriptionText = view.findViewById(R.id.transaction_description_text)
        transactionOtherAccountText = view.findViewById(R.id.transaction_other_account_text)
        balanceBeforeText = view.findViewById(R.id.balance_before_text)
        balanceAfterText = view.findViewById(R.id.balance_after_text)
        return view
    }

    override fun onStart() {
        super.onStart()
        viewModel.transactionId = transactionId ?: ""
        viewModel.transactionDate = transactionDate ?: ""
        viewModel.fetchData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTransaction().observe(this, Observer {
            when (it.responseType) {
                Status.SUCCESSFUL -> {
                    it.data?.let { it1 -> setupDetailView(it1) }
                }
                Status.LOADING -> {

                }
                Status.ERROR -> {

                }
            }
        })
    }

    private fun setupDetailView(data: Transaction) {
        transactionIdText.text = data.id
        transactionAmountText.text = data.amountFormatted
        transactionDateText.text = data.dateFormatted
        transactionDescriptionText.text = data.description
        transactionOtherAccountText.text = data.otherAccount
        balanceBeforeText.text = "todo balance before"
        balanceAfterText.text =  "todo balance after"
    }

    companion object {
        val TAG = TransactionDetailFragment::class.java.simpleName
    }

}