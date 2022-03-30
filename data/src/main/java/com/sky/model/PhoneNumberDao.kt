package com.sky.model



import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single


@Dao
interface PhoneNumberDao {
    @Query("SELECT * FROM phoneNumbers")
    fun getPhoneNumbers(): Single<List<PhoneNumberDTO>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(word: PhoneNumberDTO) : Completable

    @Query("SELECT * FROM phoneNumbers ORDER BY ID ASC")
    fun getPhoneNumbersAscOrder():Single<List<PhoneNumberDTO>>

    @Query("SELECT * FROM phoneNumbers ORDER BY ID DESC")
    fun getPhoneNumbersDescOrder():Single<List<PhoneNumberDTO>>
}