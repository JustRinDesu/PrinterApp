package com.example.printingapp.data

import com.example.printingapp.model.User

data class PrinterAppUiState (
    var my_user: User? = null,
    var location: String = "",
)