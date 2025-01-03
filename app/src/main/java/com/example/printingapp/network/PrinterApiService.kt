package com.example.printingapp.network

import com.example.printingapp.data.FileResponse
import com.example.printingapp.model.Order
import com.example.printingapp.model.User
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface PrinterApiService {

    @GET("orders")
    suspend fun getAllOrders(): List<Order>

    @GET("orders/{id}")
    suspend fun getOrderById(@Path("id") id: String): Order

    @POST("orders")
    @Headers("X-Custom-Header: printerAppSecurity")
    suspend fun createOrder(@Body newOrder: Order): Response<Void>

    @PUT("orders/{id}")
    suspend fun updateOrder(@Path("id") id: String, @Body updatedOrder: Order): Response<Void>

    @DELETE("orders/{id}")
    suspend fun deleteOrder(@Path("id") id: String): Response<Void>

    @POST("user/login")
    @Headers("X-Custom-Header: printerAppSecurity")
    suspend fun loginUser(@Body user: User): Response<User>

    @Multipart
    @POST("/upload")
    @Headers("X-Custom-Header: printerAppSecurity")
    suspend fun uploadFile(@Part file: MultipartBody.Part): FileResponse

}
