package com.samuelepontremoli.bankingapp.ui.main

import com.samuelepontremoli.data.network.dto.Transaction

interface ItemClickListener {

    fun itemClicked(transaction: Transaction)

}
