package com.sky.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.sky.domain.models.PhoneNumberModel
import io.reactivex.Completable
import io.reactivex.Single


@Dao
interface PhoneNumberDao {
    @Query("SELECT * FROM phoneNumbers")
    fun getPhoneNumbers(): Single<List<PhoneNumberDTO>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(word: PhoneNumberDTO) : Completable

    @Query("SELECT * FROM phoneNumbers ORDER BY ID ASC")
    fun getPhoneNumbersAscOrder():List<Single<PhoneNumberDTO>>

    @Query("SELECT * FROM phoneNumbers ORDER BY ID DESC")
    fun getPhoneNumbersDescOrder():List<Single<PhoneNumberDTO>>
}