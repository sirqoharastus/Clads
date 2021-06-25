package com.decagonhq.clads.di

import android.content.Context
import android.content.SharedPreferences
import com.decagonhq.clads.utils.SharedPreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharePreferenceModule {

    @Singleton
    @Provides
    fun providesSharedPreference(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("Shared_pref", Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun providesSharedPreferenceManager(sharedPreferences: SharedPreferences): SharedPreferenceManager {
        return SharedPreferenceManager(sharedPreferences)
    }
}
