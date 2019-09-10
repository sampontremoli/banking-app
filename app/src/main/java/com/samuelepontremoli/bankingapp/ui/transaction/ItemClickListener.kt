package com.samuelepontremoli.bankingapp.ui.transaction

import com.samuelepontremoli.data.network.entities.Transaction

interface ItemClickListener {

    fun itemClicked(transaction: Transaction)

}
