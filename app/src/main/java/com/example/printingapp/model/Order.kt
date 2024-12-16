package com.example.printingapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Order(
    @SerialName("order_id")
    val order_id: String?,
    val order_name: String?,
    val location: String,
    val status: String,
    val customer_id: String,
    val admin_id: String?,
    val orderDate: String,
    val finishedDate: String?,
    val print_detail: PrintDetail
)

@Serializable
data class PrintDetail(
    val print_detail_id: String,
    val no_of_copy: Int,
    val paper_type: String,
    val paper_width: Double,
    val paper_height: Double,
    val is_color: Int,
    val file_id: String
)