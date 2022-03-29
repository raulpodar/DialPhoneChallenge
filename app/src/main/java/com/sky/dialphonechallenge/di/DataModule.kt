package com.sky.dialphonechallenge.di

import android.content.SharedPreferences
import com.sky.data.DialRepositoryImpl
import com.sky.data.PhoneNumberRepositoryImpl
import com.sky.domain.repositories.DialRepository
import com.sky.model.PhoneNumberDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

//    @Provides
//    @Singleton
//    fun provideDialRepository(sharedPreferences: SharedPreferences):DialRepository{
//        return DialRepositoryImpl(sharedPreferences)
//    }

    @Provides
    @Singleton
    fun provideDialRepository(dao: PhoneNumberDao):PhoneNumberDao{
        return PhoneNumberRepositoryImpl(dao)
    }
}