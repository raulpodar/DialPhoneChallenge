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
                currentList.add(number)
                sharedPreferences
                    .edit()
                    .putStringSet(DIALED_NUMBERS_KEY, currentList)
                    .apply()
                Completable.complete()
            }
    }

    override fun getNumber() : Single<MutableSet<String>> {

        return Single.fromCallable{
                sharedPreferences.getStringSet(DIALED_NUMBERS_KEY, mutableSetOf()  )?.toMutableSet()
        }
    }


}