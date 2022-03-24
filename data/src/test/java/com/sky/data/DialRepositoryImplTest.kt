package com.sky.data

import android.content.SharedPreferences
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class DialRepositoryImplTest{

    @Mock
    private lateinit var sharedPreferences: SharedPreferences
    @Mock
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    private lateinit var cut: DialRepositoryImpl

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
        whenever(sharedPreferences.edit()).thenReturn(sharedPreferencesEditor)

        cut = DialRepositoryImpl(sharedPreferences)
    }


    @Test
    fun `given shared preferences empty when add number then save to shared preferences`(){
        //Given
        val existingNumbers=""
        val numberToBeSaved="123"

        whenever(sharedPreferences.getString(DIALED_NUMBERS_KEY,"")).thenReturn(existingNumbers)
        whenever(sharedPreferencesEditor.putString(DIALED_NUMBERS_KEY,numberToBeSaved)).thenReturn(sharedPreferencesEditor)

        //When
        val testObserver = cut.updateNumber(numberToBeSaved).test()


        //Then
        testObserver
            .assertComplete()
            .assertNoErrors()

        verify(sharedPreferencesEditor).putString(DIALED_NUMBERS_KEY,numberToBeSaved)

    }

    @Test
    fun `given numbers are saved in shared preferences when add new number`(){


        //Given
        val existingNumbers="12,34,56"
        val numberToBeSaved="78"
        val expectedString="12,34,56,78"

        whenever(sharedPreferences.getString(DIALED_NUMBERS_KEY,"")).thenReturn(existingNumbers)
        whenever(sharedPreferencesEditor.putString(DIALED_NUMBERS_KEY,expectedString)).thenReturn(sharedPreferencesEditor)

        //When
        val testObserver = cut.updateNumber(numberToBeSaved).test()


        //Then
        testObserver
        .assertComplete()
        .assertNoErrors()

        verify(sharedPreferencesEditor).putString(DIALED_NUMBERS_KEY,expectedString)
    }

    @Test
    fun `when get number with no previously saved number return empty string`(){

        //Given
        val existingNumbers=""

        whenever(sharedPreferences.getString(DIALED_NUMBERS_KEY,"")).thenReturn(existingNumbers)

        //When
        val testObserver = cut.getNumber().test()

        //Then
        testObserver
            .assertComplete()
            .assertNoErrors()
            .assertValue { list-> list=="" }
    }

    @Test
    fun `when get number with saved numbers return the numbers as string`() {
        //Given
        val existingNumbers = "123,345,567"
        whenever(sharedPreferences.getString(DIALED_NUMBERS_KEY, "")).thenReturn(existingNumbers)

        //When
        val testObserver = cut.getNumber().test()

        //Then
        testObserver
            .assertComplete()
            .assertNoErrors()
            .assertValue {
                it.equals("123,345,567")
            }
    }
}
