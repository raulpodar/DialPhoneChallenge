package com.mappers

import android.content.res.Resources
import com.sky.domain.models.PhoneNumberModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class DialPhoneNumbersDomainToPresentationMapperTest



private lateinit var cut: DialPhoneNumbersDomainToPresentationMapperTest

@Before
fun setUp() {
    cut = DialPhoneNumbersDomainToPresentationMapperTest()
}

@Test
fun `given invoked with domain model when mapping then return expected ui model`(){
    // Given
    val typedNumber = ""
    val dialedPhoneNumbers = listOf<String>("1","2","3")
    val shouldShowDial = false
    whenever(resources.getString(R.string.launch_count_label_format, launchCount)).thenReturn(launchCountMessage)
    whenever(resources.getString(R.string.rate_me_message)).thenReturn(rateMeMessage)

    val model = mock<PhoneNumberModel> {
        on { shouldShowDial } doReturn true
    }

    // When
    val result = cut(model)

    // Then
    with(result) {
        assertEquals(model.shouldPrompt, shouldPrompt)
        assertEquals(launchCountMessage, this.launchCountMessage)
        assertEquals(rateMeMessage, this.shouldPromptMessage)
    }
}