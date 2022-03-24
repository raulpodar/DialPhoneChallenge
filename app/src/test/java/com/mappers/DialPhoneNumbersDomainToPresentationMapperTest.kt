package com.mappers

import android.content.res.Resources
import com.sky.dialphonechallenge.presentation.mappers.DialPhoneNumbersDomainToPresentationMapper
import com.sky.domain.models.PhoneNumberModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class DialPhoneNumbersDomainToPresentationMapperTest {


    private lateinit var cut: DialPhoneNumbersDomainToPresentationMapper

    @Before
    fun setUp() {
        cut = DialPhoneNumbersDomainToPresentationMapper()
    }

    @Test
    fun `given invoked with domain model when mapping then return expected ui model`() {
        // Given
        val phoneNumberModel = mock<PhoneNumberModel> {
            on { shouldShowDial } doReturn true
            on { dialedPhonedNumbers } doReturn listOf()
            on { typedNumber } doReturn "123"
        }


        //When
        val result = cut(phoneNumberModel)


        // Then
        with(result) {
            assertEquals(phoneNumberModel.shouldShowDial, this.shouldShowDialButton)
            assertEquals(phoneNumberModel.typedNumber, this.currentTypedPhoneNumber)
            assertEquals(phoneNumberModel.dialedPhonedNumbers, this.dialedPhoneNumbers)
        }
    }
}