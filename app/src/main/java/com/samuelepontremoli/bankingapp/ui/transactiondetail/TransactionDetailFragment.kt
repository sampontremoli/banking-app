package com.samuelepontremoli.bankingapp.ui.transactiondetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.samuelepontremoli.bankingapp.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class TransactionDetailFragment : Fragment() {

    private val transactionId: String? by lazy {
        val safeArgs = arguments?.let { TransactionDetailFragmentArgs.fromBundle(it) }
        safeArgs?.transactionId
    }

    private val transactionDetail: TransactionDetailViewModel by viewModel()

    companion object {
        val TAG = TransactionDetailFragment::class.java.simpleName

        fun newInstance(): TransactionDetailFragment {
            return TransactionDetailFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_transaction_detail, container, false)
        return view
    }

}