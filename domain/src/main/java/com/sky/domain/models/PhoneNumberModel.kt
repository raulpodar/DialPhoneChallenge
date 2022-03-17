package com.sky.domain.models

data class PhoneNumberModel(
    val typedNumber: String="",
    val dialedPhonedNumbers: List<String> = arrayListOf<String>(),
    val shouldShowDial: Boolean =false
)
