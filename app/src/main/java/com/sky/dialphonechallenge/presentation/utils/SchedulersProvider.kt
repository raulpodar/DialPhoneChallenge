package com.sky.dialphonechallenge.presentation.utils

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SchedulersProvider @Inject constructor() {

    fun io(): Scheduler = Schedulers.io()
    fun main(): Scheduler = AndroidSchedulers.mainThread()
}