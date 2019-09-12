package com.samuelepontremoli.bankingapp.ui.main

import com.samuelepontremoli.data.network.entities.Transaction

interface ItemClickListener {

    fun itemClicked(transaction: Transaction)

}
