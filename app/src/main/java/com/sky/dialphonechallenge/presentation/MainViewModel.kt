package com.sky.dialphonechallenge.presentation

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sky.dialphonechallenge.presentation.mappers.DialPhoneNumbersDomainToPresentationMapper
import com.sky.dialphonechallenge.presentation.uiModels.DialPhoneNumberUiModel
import com.sky.dialphonechallenge.presentation.utils.SchedulersProvider
import com.sky.domain.models.PhoneNumberModel
import com.sky.domain.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    private val isValidPhoneNumberUseCase: IsValidPhoneNumberUseCase,
    private val showDialedNumberUseCase: ShowDialedNumberUseCase,
    private val userHasDialedUseCase: UserHasDialedUseCase,
    private val userHasTypedUseCase: UserHasTypedUseCase,
    private val userHasOpenedAppUseCase: UserHasOpenedAppUseCase,
    private val userHasDeletedUseCase: UserHasDeletedUseCase,
    private val dialPhoneNumbersDomainToPresentationMapper: DialPhoneNumbersDomainToPresentationMapper,
    private val schedulersProvider: SchedulersProvider
):ViewModel(){
    internal val uiModelLiveData: MutableLiveData<DialPhoneNumberUiModel> = MutableLiveData()
    var domainModel:PhoneNumberModel = PhoneNumberModel()
    private val compositeDisposable = CompositeDisposable()

    init {
        Log.v("initialise", "heyyyy")
    }
    fun onAppLaunched(){
        val disposable=userHasOpenedAppUseCase.buildUseCase()
            .map { domainModel2->
                domainModel=domainModel2
                dialPhoneNumbersDomainToPresentationMapper(domainModel)
            }
            .onErrorReturn {
                DialPhoneNumberUiModel("null",  arrayListOf("bad errror"), false)
            }
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.main())
            .subscribe { uiModel ->
                uiModelLiveData.postValue(uiModel)
            }
//            .map { DialPhoneNumbersDomainToPresentationMapper() }
//            .subscribeOn(schedulersProvider.io())
//            .observeOn(schedulersProvider.main())
//            .subscribe( uiModel->

        compositeDisposable.add(disposable)
    }

//    fun onUserHasClicked(){
//        val disposable=UserHasTypedUseCase.buildUseCase()
//
//    }

    fun userHasTyped(number:String){
        val disposable=userHasTypedUseCase.buildUseCase(number,domainModel)
            .map { newDomainModel->
                dialPhoneNumbersDomainToPresentationMapper(newDomainModel)
            }
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.main())
            .subscribe { uiModel ->
                uiModelLiveData.postValue(uiModel)
            }

        compositeDisposable.add(disposable)
    }

    fun userHasDialed(number:String){
        val disposable=userHasDialedUseCase.buildUseCase(number)
            .map { newDomainModel->
                dialPhoneNumbersDomainToPresentationMapper(newDomainModel)
            }
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.main())
            .subscribe { uiModel ->
                Log.v("listt",uiModel.dialedPhoneNumbers.toString())
                uiModelLiveData.postValue(uiModel)
            }


        compositeDisposable.add(disposable)
    }
    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun userHasDeleted(oldString:String) {
        val disposable=userHasDeletedUseCase.buildUseCase(oldString,domainModel)
            .map { newDomainModel->
                dialPhoneNumbersDomainToPresentationMapper(newDomainModel)
            }
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.main())
            .subscribe { uiModel ->
                Log.v("listt",uiModel.dialedPhoneNumbers.toString())
                uiModelLiveData.postValue(uiModel)
            }

        compositeDisposable.add(disposable)

    }


}