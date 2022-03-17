package com.sky.domain.usecases

import com.sky.domain.models.PhoneNumberModel
import com.sky.domain.repositories.DialRepository
import io.reactivex.Single
import javax.inject.Inject

class UserHasTypedUseCase @Inject constructor(
    private val dialRepository: DialRepository
){
    fun buildUseCase(number:String, currentModel: PhoneNumberModel ): Single<PhoneNumberModel> {
        var shouldShow=number.length==11
        return Single.fromCallable{
                PhoneNumberModel(
                    typedNumber =number,
                    dialedPhonedNumbers = currentModel.dialedPhonedNumbers,
                    shouldShowDial = shouldShow
                )
        }

    }
}