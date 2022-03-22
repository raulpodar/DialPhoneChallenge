package com.sky.domain.usecases

import com.sky.domain.models.PhoneNumberModel
import com.sky.domain.repositories.DialRepository
import io.reactivex.Single
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import javax.swing.text.DefaultEditorKit


@RunWith(Parameterized::class)
class UserHasDeletedUseCaseTest(
    private val oldString: String,
    private val phoneNumberModel: PhoneNumberModel
){


    companion object{
        @JvmStatic
        @Parameterized.Parameters(name = "User has deleted from string {} expected new string{}")


        fun data():List<Array<Any?>>{
            return listOf(
                arrayOf("1",PhoneNumberModel("", dialedPhonedNumbers = listOf(), shouldShowDial = false )),
                arrayOf("",PhoneNumberModel("", dialedPhonedNumbers = listOf(), shouldShowDial = false )),
                arrayOf("11",PhoneNumberModel("1", dialedPhonedNumbers = listOf(), shouldShowDial = false )),
                arrayOf("123456789012",PhoneNumberModel("12345678901", dialedPhonedNumbers = listOf(), shouldShowDial = true )),
            )
        }

    }

    @Mock
    private lateinit var dialRepository: DialRepository

    private lateinit var cut: UserHasDeletedUseCase

    private lateinit var model: PhoneNumberModel
    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
        cut = UserHasDeletedUseCase()
        model = PhoneNumberModel(typedNumber = "123", dialedPhonedNumbers = listOf("1","2","3"),shouldShowDial = false )
    }

    @Test
    fun `given user has deleted when userHasDeletedUseCase then return expected PhoneNMmber domain model`(){
        // Given
        whenever(cut.buildUseCase(oldString="123", currentModel = model)).thenReturn(Single.just(phoneNumberModel))

        // When
        val result = cut.buildUseCase(oldString = "123", currentModel = model).test()

        // Then
        result.assertValue { it.typedNumber == phoneNumberModel.typedNumber }
            .assertComplete()
            .assertNoErrors()


    }
}