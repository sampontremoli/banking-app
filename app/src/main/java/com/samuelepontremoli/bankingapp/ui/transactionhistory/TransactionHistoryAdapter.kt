package com.samuelepontremoli.bankingapp.ui.transactionhistory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samuelepontremoli.bankingapp.R
import com.samuelepontremoli.bankingapp.models.Account
import com.samuelepontremoli.bankingapp.models.BaseItem
import com.samuelepontremoli.bankingapp.ui.main.ItemClickListener
import com.samuelepontremoli.bankingapp.models.Transaction
import kotlinx.android.synthetic.main.item_account.view.*
import kotlinx.android.synthetic.main.item_transaction.view.*

class TransactionHistoryAdapter(private val itemClickListener: ItemClickListener?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dataSet = mutableListOf<BaseItem>()

    override fun getItemCount(): Int = dataSet.size

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            isHeader() -> ItemType.TYPE_ACCOUNT.ordinal
            else -> ItemType.TYPE_TRANSACTION.ordinal
        }
    }

    private fun isHeader(): Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemType.TYPE_ACCOUNT.ordinal -> AccountInfoViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_account, parent, false
                )
            )
            else -> TransactionViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_transaction,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TransactionViewHolder -> holder.bind(dataSet[position] as Transaction, itemClickListener)
            is AccountInfoViewHolder -> holder.bind(dataSet[position] as Account)
        }
    }

    fun updateList(list: List<BaseItem>) {
        if (list.isNotEmpty()) {
            dataSet.clear()
            dataSet.addAll(list)
            notifyDataSetChanged()
        }
    }

    private class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(transaction: Transaction, itemClickListener: ItemClickListener?) {
            with(itemView) {
                transaction_amount.text = transaction.amountFormatted
                transaction_description.text = transaction.description
                transaction_date.text = transaction.dateFormatted
                setOnClickListener {
                    itemClickListener?.itemClicked(transaction)
                }
            }
        }

    }

    private class AccountInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(account: Account) {
            with(itemView) {
                balance_text.text = account.balanceFormatted
            }
        }

    }

    private enum class ItemType {
        TYPE_TRANSACTION,
        TYPE_ACCOUNT
    }

}
