package com.sky.domain.usecases

import com.sky.domain.models.PhoneNumberModel
import io.reactivex.Single
import javax.inject.Inject


class UserHasDeletedUseCase
@Inject constructor(){


        fun buildUseCase(oldString:String, currentModel:PhoneNumberModel): Single<PhoneNumberModel> {
            lateinit var  newString:String
            if(oldString.length>0){
                newString=oldString.subSequence(0,oldString.length-1).toString()
            }
            var shouldShow=newString.length==11
            return Single.fromCallable{
                PhoneNumberModel(
                    typedNumber =newString,
                    dialedPhonedNumbers = currentModel.dialedPhonedNumbers,
                    shouldShowDial = shouldShow
                )
            }

        }

}