package com.samuelepontremoli.bankingapp.ui.main

import com.samuelepontremoli.bankingapp.models.Transaction

interface ItemClickListener {

    fun itemClicked(transaction: Transaction)

}
