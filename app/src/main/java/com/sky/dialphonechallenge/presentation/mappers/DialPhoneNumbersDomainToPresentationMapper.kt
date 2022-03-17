package com.sky.dialphonechallenge.presentation.mappers

import com.sky.dialphonechallenge.presentation.uiModels.DialPhoneNumberUiModel
import com.sky.domain.models.PhoneNumberModel
import javax.inject.Inject

class DialPhoneNumbersDomainToPresentationMapper
@Inject constructor(){

    operator fun invoke(domainModel:PhoneNumberModel):DialPhoneNumberUiModel{
        return DialPhoneNumberUiModel(
            currentTypedPhoneNumber = domainModel.typedNumber,
            dialedPhoneNumbers = domainModel.dialedPhonedNumbers,
            shouldShowDialButton = domainModel.shouldShowDial

        )
    }
}