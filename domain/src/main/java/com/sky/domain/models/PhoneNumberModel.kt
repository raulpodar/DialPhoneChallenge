package com.sky.domain.models

data class PhoneNumberModel(
    val typedNumber: String="",
    val dialedPhonedNumbers: MutableSet<String> = mutableSetOf("32","343"),
    val shouldShowDial: Boolean =false
)
