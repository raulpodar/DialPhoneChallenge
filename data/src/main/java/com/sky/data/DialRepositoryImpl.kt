package com.sky.data

import android.content.SharedPreferences
import javax.inject.Inject
import com.sky.domain.repositories.DialRepository
import io.reactivex.Completable
import io.reactivex.Single

const val DIALED_NUMBERS_KEY = "dialed_numbers"

class DialRepositoryImpl
@Inject constructor(private val sharedPreferences: SharedPreferences):DialRepository{

    override fun writeNumber(number: String) {

    }

    override fun updateNumber(number:String):Completable {
        return getNumber()
            .flatMapCompletable { currentList->
                var newList:String
                if (currentList!=""){
                    newList = currentList + "," + number
                }
                else{
                    newList=number
                }
                sharedPreferences
                    .edit()
                    .putString(DIALED_NUMBERS_KEY, newList)
                    .apply()
                Completable.complete()
            }
    }

    override fun getNumber() : Single<String> {

        return Single.fromCallable{
                sharedPreferences.getString(DIALED_NUMBERS_KEY, "")
        }
    }


}