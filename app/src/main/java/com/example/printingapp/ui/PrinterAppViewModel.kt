package com.example.printingapp.ui

import androidx.lifecycle.ViewModel
import com.example.printingapp.data.PrinterAppUiState
import com.example.printingapp.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PrinterAppViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PrinterAppUiState())
    var uiState: StateFlow<PrinterAppUiState> = _uiState.asStateFlow()

    fun update_user(user: User){
        _uiState.update { currentState ->
            currentState.copy(
                my_user = user
            )
        }
    }

    fun get_user() : User?{
        return uiState.value.my_user
    }

}