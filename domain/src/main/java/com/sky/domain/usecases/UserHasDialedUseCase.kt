package com.sky.domain.usecases

import com.sky.domain.models.PhoneNumberModel
import com.sky.domain.repositories.DialRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Single
import javax.inject.Inject

class UserHasDialedUseCase @Inject constructor(
    private val dialRepository: DialRepository
){
    fun buildUseCase(number:String): Single<PhoneNumberModel> {
        return dialRepository.updateNumber(number)
            .andThen(dialRepository.getNumber())
            .map{ list->
                var dialedPhoneNumbers2=list.split(",")

                PhoneNumberModel(typedNumber = "", dialedPhonedNumbers = dialedPhoneNumbers2,shouldShowDial = false)
            }

    }
}