package com.decagonhq.clads.di

import com.decagonhq.clads.service.remoteserviceapi.UserLoginServiceApi
import com.decagonhq.clads.service.remoteserviceapi.UserRegistrationServiceApi
import com.decagonhq.clads.utils.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideClient(logging: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    @Singleton
    fun provideUserRegistrationService(retrofit: Retrofit): UserRegistrationServiceApi =
        retrofit.create(UserRegistrationServiceApi::class.java)

    @Singleton
    @Provides
    fun providesUserLoginService(retrofit: Retrofit): UserLoginServiceApi =
        retrofit.create(UserLoginServiceApi::class.java)
}
