package com.decagonhq.clads.models

data class UserRegistrationResponse(
    val status: Int,
    val message: String,
    val payload: String
)
