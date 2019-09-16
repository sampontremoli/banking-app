package com.samuelepontremoli.bankingapp.ui.transactionhistory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samuelepontremoli.bankingapp.R
import com.samuelepontremoli.bankingapp.ui.main.ItemClickListener
import com.samuelepontremoli.bankingapp.models.Transaction
import kotlinx.android.synthetic.main.item_transaction.view.*

class TransactionHistoryAdapter(private val itemClickListener: ItemClickListener?) :
    RecyclerView.Adapter<TransactionHistoryAdapter.TransactionViewHolder>() {

    private var transactions = mutableListOf<Transaction>()

    override fun getItemCount(): Int = transactions.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(transactions[position], itemClickListener)
    }

    fun updateList(list: List<Transaction>) {
        if (list.isNotEmpty()) {
            transactions.clear()
            transactions.addAll(list)
            notifyDataSetChanged()
        }
    }

    class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(transaction: Transaction, itemClickListener: ItemClickListener?) {
            with(itemView) {
                transaction_id.text = transaction.id
                transaction_amount.text = transaction.amount.toString()
                transaction_description.text = transaction.description
                transaction_other_account.text = transaction.otherAccount
                transaction_date.text = transaction.date
                setOnClickListener {
                    itemClickListener?.itemClicked(transaction)
                }
            }
        }

    }

}
