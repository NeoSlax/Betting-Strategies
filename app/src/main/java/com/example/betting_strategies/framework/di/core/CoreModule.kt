package com.example.betting_strategies.framework.di.core

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.betting_strategies.business.data.MyCache
import com.example.betting_strategies.framework.data.MyCacheImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreModule {

    @Provides
    @Singleton
    fun providesPreferences(application: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }

    @Singleton
    @Provides
    fun provideMyCacheImpl(sharedPreferences: SharedPreferences): MyCache {
        return MyCacheImpl(sharedPreferences)
    }
}