package com.sky.domain.usecases

import com.sky.domain.models.PhoneNumberModel
import com.sky.domain.repositories.DialRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runners.Parameterized
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class UserHasTypedUseCaseTest (){

    @Mock
    private lateinit var dialRepository: DialRepository

    private lateinit var cut: UserHasTypedUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        cut = UserHasTypedUseCase(dialRepository)
    }

    @Test
    fun `given typed number when execute usecase then return new phoneNumberModel`() {
        //Given
        val number = "1234"
        val phoneNumberModel = mock<PhoneNumberModel>{
                on {typedNumber} doReturn "1234"
                on {dialedPhonedNumbers} doReturn listOf("123","345","567")
                on {shouldShowDial} doReturn false
        }
        val dialledNumbers= "123,345,567"
        whenever(dialRepository.getNumber()).thenReturn(Single.just(dialledNumbers))

        //When
        val testObserver = cut.buildUseCase(number, phoneNumberModel).test()

        //Then
        testObserver
            .assertComplete()
            .assertNoErrors()
            .assertValue{model-> model.shouldShowDial==false}
            .assertValue { model-> model.typedNumber==phoneNumberModel.typedNumber }
            .assertValue { model-> model.dialedPhonedNumbers==listOf("123","345","567") }



    }

    @Test
    fun `given error obtaining dialled numbers when execute userIsTyping  use case then return base domain model `() {
        // Given
        whenever(dialRepository.getNumber()).thenReturn(Single.error(Throwable()))
        val number = "1234"
        val phoneNumberModel = mock<PhoneNumberModel>{
            on {typedNumber} doReturn "1234"
            on {dialedPhonedNumbers} doReturn listOf("123","345","567")
            on {shouldShowDial} doReturn false

        }
        // When
        val testObserver = cut.buildUseCase(number,phoneNumberModel).test()

        // Then
        testObserver
            .assertComplete()
            .assertNoErrors()
            .assertValue { model-> model.dialedPhonedNumbers==emptyList<String>() }
            .assertValue{model->model.typedNumber==number}
            .assertValue{model->model.shouldShowDial==false}

    }

}