package com.example.printingapp.data

import com.example.printingapp.network.PrinterApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val printerAppRepository: PrinterAppRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "http://157.245.204.192:3000"

    /**
     * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
     */
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    /**
     * Retrofit service object for creating api calls
     */
    private val retrofitService: PrinterApiService by lazy {
        retrofit.create(PrinterApiService::class.java)
    }

    /**
     * DI implementation for Mars photos repository
     */
    override val printerAppRepository: PrinterAppRepository by lazy {
        NetworkPrinterAppRepository(retrofitService)
    }
}