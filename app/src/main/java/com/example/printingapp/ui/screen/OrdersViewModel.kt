import retrofit2.HttpException
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
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

sealed interface OrdersUiState {
    data class Success(val orders: List<Order>) : OrdersUiState
    data class OrderModificationSuccess(val response: Response<Void>) : OrdersUiState
    data class Error(val exception: Throwable? = null) : OrdersUiState
    object Loading : OrdersUiState
}

class OrdersViewModel(private val printAppRepository: PrinterAppRepository) : ViewModel() {
    var ordersUiState: OrdersUiState by mutableStateOf(OrdersUiState.Loading)
        private set

    init {
    }

    fun getAllOrders() {
        viewModelScope.launch {
            ordersUiState = OrdersUiState.Loading
            ordersUiState = try {
                OrdersUiState.Success(printAppRepository.getAllOrders())
            } catch (e: IOException) {
                OrdersUiState.Error(e)
            } catch (e: HttpException) {
                OrdersUiState.Error(e)
            }
        }
    }

    fun createOrder(newOrder: Order) {
        viewModelScope.launch {
            ordersUiState = OrdersUiState.Loading
            ordersUiState = try {
                val response = printAppRepository.createOrder(newOrder)

                // Check if the response code is 201
                if (response.code() == 201) {
                    // Trigger OrderModificationSuccess only when the status code is 201
                    OrdersUiState.OrderModificationSuccess(response)
                } else {
                    // Handle other status codes that are not 201
                    OrdersUiState.Error(Exception("Failed to create order. Status code: ${response.code()}"))
                }
            } catch (e: IOException) {
                OrdersUiState.Error(e)
            } catch (e: HttpException) {
                OrdersUiState.Error(e)
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PrinterApplication)
                val printAppRepository = application.container.printerAppRepository
                OrdersViewModel(printAppRepository = printAppRepository)
            }
        }
    }
}