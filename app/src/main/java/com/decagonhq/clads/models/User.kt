package com.decagonhq.clads.models

import com.google.gson.annotations.SerializedName

data class User(
    val category: String,
    @SerializedName("deliveryddress")
    val deliveryAddress: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val password: String,
    val phoneNumber: String,
    val role: String,
    val gender: String? = null,
    val thumbnail: String? = null,
    val country: String? = null
)
