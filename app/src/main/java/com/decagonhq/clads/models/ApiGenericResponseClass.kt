package com.decagonhq.clads.models

data class ApiGenericResponseClass<T> (
    val message: String,
    val payload: T,
    val status: Int

)
