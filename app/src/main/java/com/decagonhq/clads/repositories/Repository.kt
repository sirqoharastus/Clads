package com.decagonhq.clads.repositories

import com.decagonhq.clads.models.User
import com.decagonhq.clads.service.remoteserviceapi.UserRegistrationServiceApi
import javax.inject.Inject

class Repository @Inject constructor(private val userRegistrationService: UserRegistrationServiceApi) :
    BaseRepository() {

    suspend fun registerUser(user: User) = safeApiCall {

        userRegistrationService.registerUser(user)
    }
}
