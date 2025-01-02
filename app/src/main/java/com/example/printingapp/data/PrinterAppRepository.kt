package com.example.printingapp.data

import com.example.printingapp.model.Order
import com.example.printingapp.model.User
import com.example.printingapp.network.PrinterApiService
import retrofit2.Response

interface PrinterAppRepository {
    suspend fun getAllOrders(): List<Order>
    suspend fun getOrderById(id: String): Order
    suspend fun createOrder(newOrder: Order): Response<Void>
    suspend fun updateOrder(id: String, updatedOrder: Order): Response<Void>
    suspend fun deleteOrder(id: String): Response<Void>

    suspend fun loginUser(user: User): Response<User>
}

class NetworkPrinterAppRepository(
    private val printerApiServices: PrinterApiService


) : PrinterAppRepository {
    override suspend fun getAllOrders(): List<Order> = printerApiServices.getAllOrders()
    override suspend fun getOrderById(id: String): Order = printerApiServices.getOrderById(id)
    override suspend fun createOrder(newOrder: Order): Response<Void> =
        printerApiServices.createOrder(newOrder)
    override suspend fun updateOrder(id: String, updatedOrder: Order): Response<Void> =
        printerApiServices.updateOrder(id, updatedOrder)
    override suspend fun deleteOrder(id: String): Response<Void> =
        printerApiServices.deleteOrder(id)

    override suspend fun loginUser(user: User): Response<User> {
        return printerApiServices.loginUser(user)
    }
}
