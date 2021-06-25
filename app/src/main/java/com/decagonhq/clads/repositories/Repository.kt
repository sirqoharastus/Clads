package com.decagonhq.clads.repositories

import com.decagonhq.clads.models.User
import com.decagonhq.clads.models.UserLogin
import com.decagonhq.clads.models.UserRole
import com.decagonhq.clads.service.remoteserviceapi.UserLoginServiceApi
import com.decagonhq.clads.service.remoteserviceapi.UserRegistrationServiceApi
import javax.inject.Inject

class Repository @Inject constructor(
    private val userRegistrationService: UserRegistrationServiceApi,
    private val userLoginService: UserLoginServiceApi
) :
    BaseRepository() {

    suspend fun registerUser(user: User) = safeApiCall {

        userRegistrationService.registerUser(user)
    }

    suspend fun loginUser(user: UserLogin) = safeApiCall {
        userLoginService.loginWithEmail(user)
    }

    suspend fun loginWithGoogle(header: String, role: UserRole) = safeApiCall {
        userLoginService.loginWithGoogle(header, role)
    }
}
