package com.decagonhq.clads.di

import com.decagonhq.clads.repositories.Repository
import com.decagonhq.clads.service.remoteserviceapi.UserRegistrationServiceApi
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    fun provideApplicationRepositoryInstance(userRegistrationServiceApi: UserRegistrationServiceApi): Repository {
        return Repository(userRegistrationServiceApi)
    }
}
