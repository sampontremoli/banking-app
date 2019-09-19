package com.samuelepontremoli.bankingapp.ui.transactiondetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.samuelepontremoli.bankingapp.R
import com.samuelepontremoli.bankingapp.extensions.hide
import com.samuelepontremoli.bankingapp.extensions.show
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

    private var loadingView: FrameLayout? = null

    private var errorView: FrameLayout? = null
    private var retryCallToAction: LinearLayout? = null

    private val transactionId: String? by lazy {
        arguments?.let { TransactionDetailFragmentArgs.fromBundle(it).transactionId }
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

        loadingView = view.findViewById(R.id.loading_view)
        errorView = view.findViewById(R.id.error_view)
        retryCallToAction = view.findViewById(R.id.retry_call_to_action)
        retryCallToAction?.setOnClickListener { fetchData() }
        return view
    }

    override fun onStart() {
        super.onStart()
        viewModel.transactionId = transactionId ?: ""
        fetchData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTransaction().observe(this, Observer {
            when (it.responseType) {
                Status.SUCCESSFUL -> {
                    it.data?.let { transaction -> setupDetailView(transaction) }
                    loadingView?.hide()
                }
                Status.LOADING -> {
                    errorView?.hide()
                    loadingView?.show()
                }
                Status.ERROR -> {
                    loadingView?.hide()
                    errorView?.show()
                }
            }
        })
    }

    private fun fetchData() {
        viewModel.fetchData()
    }

    private fun setupDetailView(transaction: Transaction) {
        val currencySymbol = resources.getString(R.string.euro_symbol)
        transactionIdText.text = transaction.id
        transactionAmountText.text = transaction.amountFormatted
        transactionDateText.text = transaction.dateFormatted
        transactionDescriptionText.text = transaction.description
        transactionOtherAccountText.text = transaction.otherAccount
        val balanceBeforeDisplay = "$currencySymbol ${transaction.balanceBefore}"
        balanceBeforeText.text = balanceBeforeDisplay
        val balanceAfterTextDisplay = "$currencySymbol ${transaction.balanceAfter}"
        balanceAfterText.text = balanceAfterTextDisplay
    }

    companion object {
        val TAG = TransactionDetailFragment::class.java.simpleName
    }

}