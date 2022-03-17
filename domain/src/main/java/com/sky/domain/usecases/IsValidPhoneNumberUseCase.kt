package com.sky.domain.usecases

import com.sky.domain.models.PhoneNumberModel
import com.sky.domain.repositories.DialRepository
import io.reactivex.Single
import javax.inject.Inject

class IsValidPhoneNumberUseCase
@Inject constructor(
    private val dialRepository: DialRepository
){
    fun buildUseCase(phoneNumber: String): Boolean{
        return phoneNumber.length==11
    }
}