package com.decagonhq.clads.models

data class UserLoginResponse(
    val status: Int,
    val message: String,
    val payload: String
)
