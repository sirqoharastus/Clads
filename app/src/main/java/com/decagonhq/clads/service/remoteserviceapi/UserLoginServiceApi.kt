package com.decagonhq.clads.service.remoteserviceapi

import com.decagonhq.clads.models.UserLogin
import com.decagonhq.clads.models.UserLoginResponse
import com.decagonhq.clads.models.UserRole
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface UserLoginServiceApi {
    @POST("login")
    suspend fun loginWithEmail(@Body user: UserLogin): UserLoginResponse

    @POST("login/google")
    suspend fun loginWithGoogle(
        @Header("Authorization") header: String,
        @Body role: UserRole
    ): UserLoginResponse
}
