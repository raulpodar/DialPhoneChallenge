package com

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.sky.dialphonechallenge.presentation.MainViewModel
import com.sky.dialphonechallenge.presentation.mappers.DialPhoneNumbersDomainToPresentationMapper
import com.sky.dialphonechallenge.presentation.uiModels.DialPhoneNumberUiModel
import com.sky.dialphonechallenge.presentation.utils.SchedulersProvider
import com.sky.domain.models.PhoneNumberModel
import com.sky.domain.usecases.UserHasDeletedUseCase
import com.sky.domain.usecases.UserHasDialedUseCase
import com.sky.domain.usecases.UserHasOpenedAppUseCase
import com.sky.domain.usecases.UserHasTypedUseCase
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.*
import java.util.*

class MainViewModelTest{
    @get:Rule
    val testRule: TestRule= InstantTaskExecutorRule()

    @Mock
    private lateinit var userHasDialedUseCase: UserHasDialedUseCase

    @Mock
    private lateinit var userHasTypedUseCase: UserHasTypedUseCase

    @Mock
    private lateinit var userHasOpenedAppUseCase: UserHasOpenedAppUseCase

    @Mock
    private lateinit var userHasDeletedUseCase: UserHasDeletedUseCase

    @Mock
    private lateinit var dialPhoneNumbersDomainToPresentationMapper: DialPhoneNumbersDomainToPresentationMapper

    @Mock
    private lateinit var schedulersProvider: SchedulersProvider

    @Mock
    private lateinit var uiStateObserver: Observer<DialPhoneNumberUiModel>

    private lateinit var cut: MainViewModel


    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)

        whenever(schedulersProvider.io()).thenReturn(Schedulers.trampoline())
        whenever(schedulersProvider.main()).thenReturn(Schedulers.trampoline())

        cut= MainViewModel(
            userHasDialedUseCase,
            userHasTypedUseCase,
            userHasOpenedAppUseCase,
            userHasDeletedUseCase,
            dialPhoneNumbersDomainToPresentationMapper,
            schedulersProvider
        )
        cut.uiModelLiveData.observeForever(uiStateObserver)
    }

    @Test
    fun `when app launched then update domain model and post expected ui model to presentation`(){
        // Given
        val phoneNumberModel = mock<PhoneNumberModel> {
            on { shouldShowDial } doReturn false
        }
        val launchCount = 1
        whenever(userHasOpenedAppUseCase.buildUseCase()).thenReturn(Single.just(phoneNumberModel))
        whenever(userHasTypedUseCase.buildUseCase(number="11",phoneNumberModel)).thenReturn(Single.just(phoneNumberModel))
        whenever(userHasDeletedUseCase.buildUseCase(oldString = "11",phoneNumberModel)).thenReturn(Single.just(phoneNumberModel))
        whenever(userHasDialedUseCase.buildUseCase(number = "11")).thenReturn(Single.just(phoneNumberModel))
        val uiModel = mock<DialPhoneNumberUiModel>()
        whenever(dialPhoneNumbersDomainToPresentationMapper.invoke(phoneNumberModel)).thenReturn(uiModel)

        // When
        cut.onAppLaunched()

        cut.userHasTyped("11")
        cut.userHasDialed("11")
        cut.userHasDeleted("11")

        // Then
        verify(userHasOpenedAppUseCase).buildUseCase()
        verify(uiStateObserver, times(4)).onChanged(uiModel)

        verify(userHasTypedUseCase).buildUseCase("11",phoneNumberModel)
        verify(userHasDeletedUseCase).buildUseCase("11",phoneNumberModel)
        verify(userHasDialedUseCase).buildUseCase("11")

    }

//
//    @Test
//    fun `when user has typed then update domain model and post expected ui model to presentation`(){
//        // Given
//        val phoneNumberModel = mock<PhoneNumberModel> {
//            on { shouldShowDial } doReturn false
//        }
//        whenever(userHasTypedUseCase.buildUseCase(number="0",phoneNumberModel)).thenReturn(Single.just(phoneNumberModel))
//        val uiModel = mock<DialPhoneNumberUiModel>()
//        whenever(dialPhoneNumbersDomainToPresentationMapper.invoke(phoneNumberModel)).thenReturn(uiModel)
//
//        // When
//        cut.onAppLaunched()
//
//        // Then
//
//        verify(userHasTypedUseCase).buildUseCase("0",phoneNumberModel)
//        verify(uiStateObserver).onChanged(uiModel)
//
//    }

}