package com.sky.domain.repositories

import io.reactivex.Completable
import io.reactivex.Single

interface DialRepository {


    fun writeNumber(number: String)
    fun updateNumber(number:String):Completable
    fun getNumber(): Single<String>
}