package com.sky.dialphonechallenge.presentation.uiModels

data class DialPhoneNumberUiModel(
    val currentTypedPhoneNumber:String,
    val dialedPhoneNumbers:List<String>,
    val shouldShowDialButton:Boolean
)
