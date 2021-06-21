package com.decagonhq.clads.service.remoteserviceapi

import com.decagonhq.clads.models.User
import com.decagonhq.clads.models.UserRegistrationResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserRegistrationServiceApi {

    @POST("artisans/register")
    suspend fun registerUser(@Body user: User): UserRegistrationResponse
}
