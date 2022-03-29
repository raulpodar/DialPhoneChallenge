package com.sky.data

import com.sky.domain.models.PhoneNumberModel
import com.sky.domain.repositories.DialRepository
import com.sky.mappers.PhoneNumberDtoToDomainMapper
import com.sky.model.PhoneNumberDTO
import com.sky.model.PhoneNumberDao
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject


class PhoneNumberRepositoryImpl @Inject constructor(
    private val phoneNumberDao: PhoneNumberDao,
    private val mapper: PhoneNumberDtoToDomainMapper,
) : DialRepository {

    val listPhoneNumbers= mutableListOf<String>()



    override fun updateNumbers(number: String): Completable {
        return phoneNumberDao.insert(
            PhoneNumberDTO(
                phoneNumber = number,
            )
        )
    }

    override fun getNumbers(): Single<PhoneNumberModel> {
        return phoneNumberDao.getPhoneNumbers()
            .map { listPhoneNumbers ->
                mapper(listPhoneNumbers)
            }

    }


}

