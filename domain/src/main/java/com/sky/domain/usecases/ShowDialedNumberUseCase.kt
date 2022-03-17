package com.sky.domain.usecases

import com.sky.domain.repositories.DialRepository
import io.reactivex.Single
import javax.inject.Inject

class ShowDialedNumberUseCase
@Inject constructor(
    private val dialRepository: DialRepository
){
    fun buildUseCase(): Single<MutableSet<String>> {
        return dialRepository.getNumber()
    }
}