package com.dbs.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.nonNull(): LiveData<T> {
    val mediatorLiveData = MediatorLiveData<T>()
    mediatorLiveData.addSource(this, Observer {
        it?.let {
            mediatorLiveData.value = it
        }
    })
    return mediatorLiveData
}
