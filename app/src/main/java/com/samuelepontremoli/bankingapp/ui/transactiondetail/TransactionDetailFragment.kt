package com.samuelepontremoli.bankingapp.ui.transactiondetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.samuelepontremoli.bankingapp.R
import com.samuelepontremoli.data.network.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class TransactionDetailFragment : Fragment() {

    private val transactionId: String? by lazy {
        val safeArgs = arguments?.let { TransactionDetailFragmentArgs.fromBundle(it) }
        safeArgs?.transactionId
    }

    private val viewModel: TransactionDetailViewModel by viewModel()

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

    override fun onStart() {
        super.onStart()
        viewModel.transactionId = transactionId ?: ""
        viewModel.fetchData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTransaction().observe(this, Observer {
            when(it.responseType) {
                Status.SUCCESSFUL -> {
                    it.data
                }
                Status.LOADING -> {

                }
                Status.ERROR -> {

                }

            }
        })
    }

}