package com.samuelepontremoli.bankingapp.ui.main

import com.samuelepontremoli.data.presentation.Transaction

interface ItemClickListener {

    fun itemClicked(transaction: Transaction)

}
