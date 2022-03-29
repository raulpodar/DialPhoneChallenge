package com.sky.domain.usecases

import com.sky.domain.models.PhoneNumberModel
import com.sky.domain.repositories.DialRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class UserHasOpenedAppUseCase
@Inject constructor(
    private val dialRepository: DialRepository,
){
    fun buildUseCase(): Single<PhoneNumberModel> {
        return dialRepository.getNumbers()
    }
}