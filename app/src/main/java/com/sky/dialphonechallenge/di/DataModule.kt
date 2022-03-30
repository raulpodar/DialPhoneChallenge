package com.sky.dialphonechallenge.di

import android.content.Context
import androidx.room.Room
import com.sky.data.PhoneNumberRepositoryImpl
import com.sky.data.PhoneNumberRoomDatabase
import com.sky.domain.repositories.DialRepository
import com.sky.mappers.PhoneNumberDtoToDomainMapper
import com.sky.model.PhoneNumberDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDialRepository(dao: PhoneNumberDao, mapper: PhoneNumberDtoToDomainMapper):DialRepository{
        return PhoneNumberRepositoryImpl(dao, mapper)
    }


    @Provides
    fun providePhoneNumberDao(database: PhoneNumberRoomDatabase):PhoneNumberDao{
        return database.phoneNumberDao()
    }

    @Provides
    @Singleton
    fun providePhoneNumberRoomDatabase(@ApplicationContext appContext: Context):
        PhoneNumberRoomDatabase {
            return Room.databaseBuilder(
                appContext,
                PhoneNumberRoomDatabase::class.java,
                "phoneNumbers"
            ).build()
        }
    }


