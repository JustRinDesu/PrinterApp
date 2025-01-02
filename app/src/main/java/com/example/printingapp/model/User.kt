package com.example.printingapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User (

    val username: String,
    val password: String?,
    @SerialName("user_role")
    var userRole: String?,

)
