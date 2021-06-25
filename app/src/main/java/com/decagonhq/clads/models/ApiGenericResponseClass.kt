package com.decagonhq.clads.models

data class ApiGenericResponseClass<T> (
    val title: String?,
    val errors: ErrorResponse?,
    val data: T?

)
