package com.sky.dialphonechallenge.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import com.sky.dialphonechallenge.DemoApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideSkyGoApplication(application: Application): DemoApplication = application as DemoApplication

    @Provides
    @Singleton
    fun provideApplicationContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideResources(context: Context): Resources {
        return context.resources
    }
}