package com.sky.domain.usecases

import com.sky.domain.models.PhoneNumberModel
import com.sky.domain.repositories.DialRepository
import io.reactivex.Single
import javax.inject.Inject

class UserHasTypedUseCase @Inject constructor(
    private val dialRepository: DialRepository
){
    fun buildUseCase(number:String, currentModel: PhoneNumberModel ): Single<PhoneNumberModel> {


        return dialRepository.getNumbers()
            .map {
                var shouldShow=number.length==11
                PhoneNumberModel(
                    typedNumber =number,
                    dialedPhonedNumbers = currentModel.dialedPhonedNumbers,
                    shouldShowDial = shouldShow
                )
            }
            .onErrorReturnItem(
                PhoneNumberModel(
                typedNumber = number,
                dialedPhonedNumbers = emptyList(),
                shouldShowDial = false
                )
            )

        }

    }
