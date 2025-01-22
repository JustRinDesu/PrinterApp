package com.example.printingapp.ui.screen.common

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.printingapp.PrinterApplication
import com.example.printingapp.data.PrinterAppRepository
import com.example.printingapp.model.Order
import com.example.printingapp.model.User
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class OrderDetailsViewModel(private val printAppRepository: PrinterAppRepository) : ViewModel() {
    var orderUiState: OrderUiState by mutableStateOf(OrderUiState.Loading)
        private set
    var orderUpdateUiState: OrderUpdateUiState by mutableStateOf(OrderUpdateUiState.Loading)
        private set

    init {
    }

    sealed interface OrderUiState {
        data class Success(val order: Order) : OrderUiState
//        data class OrderModificationSuccess(val response: Response<Void>) : OrdersUiState
        data class Error(val exception: Throwable? = null) : OrderUiState
        object Loading : OrderUiState
    }

    sealed interface OrderUpdateUiState {
        data class OrderModificationSuccess(val response: Response<Void>) : OrderUpdateUiState
        data class Error(val exception: Throwable? = null) : OrderUpdateUiState
        object Loading : OrderUpdateUiState
    }

    fun getOrder(id: String) {
        viewModelScope.launch {
            orderUiState = OrderUiState.Loading
            orderUiState = try {
                OrderUiState.Success(printAppRepository.getOrderById(id))
            } catch (e: IOException) {
                OrderUiState.Error(e)
            } catch (e: HttpException) {
                OrderUiState.Error(e)
            }
        }
    }

    fun updateStatus(status: String, staff: User, id: String){
        viewModelScope.launch {
            orderUpdateUiState = OrderUpdateUiState.Loading
            orderUpdateUiState = try {
                OrderUpdateUiState.OrderModificationSuccess(printAppRepository.prepareOrder(staff,id,status))
            } catch (e: IOException) {
                OrderUpdateUiState.Error(e)
            } catch (e: HttpException) {
                OrderUpdateUiState.Error(e)
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PrinterApplication)
                val printAppRepository = application.container.printerAppRepository
                OrderDetailsViewModel(printAppRepository = printAppRepository)
            }
        }

    }


}