package com.decagonhq.clads.di

import com.decagonhq.clads.repositories.Repository
import com.decagonhq.clads.service.remoteserviceapi.UserLoginServiceApi
import com.decagonhq.clads.service.remoteserviceapi.UserRegistrationServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideApplicationRepositoryInstance(
        userRegistrationServiceApi: UserRegistrationServiceApi,
        userLoginService: UserLoginServiceApi
    ): Repository {
        return Repository(userRegistrationServiceApi, userLoginService)
    }
}
