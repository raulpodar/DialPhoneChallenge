package com.sky.domain.usecases

import com.sky.domain.repositories.DialRepository
import io.reactivex.Completable
import javax.inject.Inject

class UserHasDialedUseCase @Inject constructor(
    private val dialRepository: DialRepository
){
    fun buildUseCase(number:String): Completable {
        return dialRepository.updateNumber(number)
    }
}