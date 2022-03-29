package com.sky.domain.repositories

import com.sky.domain.models.PhoneNumberModel
import io.reactivex.Completable
import io.reactivex.Single

interface DialRepository {


    fun updateNumbers(number:String):Completable
    fun getNumbers(): Single<PhoneNumberModel>
}