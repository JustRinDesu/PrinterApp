package com.example.printingapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Order(
    val id: String,
    val location: String,
    val status: String,
    val customerID: String,
    val adminID: String? = null,
    val printDetailID: String,
    val orderDate: String,
    val finishedDate: String,
)
