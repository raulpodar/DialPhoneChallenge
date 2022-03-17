package com.sky.dialphonechallenge.presentation.uiModels

data class DialPhoneNumberUiModel(
    val currentTypedPhoneNumber:String,
    val dialedPhoneNumbers:MutableSet<String>,
    val shouldShowDialButton:Boolean
)
