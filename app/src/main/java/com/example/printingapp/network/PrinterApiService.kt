package com.example.printingapp.network

import com.example.printingapp.model.Order
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PrinterApiService {

    @GET("orders")
    suspend fun getAllOrders(): List<Order>

    @GET("orders/{id}")
    suspend fun getOrderById(@Path("id") id: String): Order

    @POST("orders")
    suspend fun createOrder(@Body newOrder: Order): Response<Void>

    @PUT("orders/{id}")
    suspend fun updateOrder(@Path("id") id: String, @Body updatedOrder: Order): Response<Void>

    @DELETE("orders/{id}")
    suspend fun deleteOrder(@Path("id") id: String): Response<Void>

}
