package com.sky.mappers

import com.sky.domain.models.PhoneNumberModel
import com.sky.model.PhoneNumberDTO
import javax.inject.Inject



class PhoneNumberDtoToDomainMapper @Inject constructor(){

    operator fun invoke(phoneNumbersList: List<PhoneNumberDTO>):PhoneNumberModel{
        return PhoneNumberModel(
            typedNumber = "",
            dialedPhonedNumbers = getPhoneNumbersAsList(phoneNumbersList),
            shouldShowDial = false

        )
    }

    fun getPhoneNumbersAsList(phoneNumbersList: List<PhoneNumberDTO>):List<String>{
        return phoneNumbersList.map { number->
            number.phoneNumber
        }
    }

}