package com.dbs.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dbs.data.article.detail.Detail

internal class DetailViewModel : ViewModel() {

    private lateinit var detail: Detail
    private val textMutableLiveData = MutableLiveData<String>()
    private val avatarMutableLiveData = MutableLiveData<String>()
    private val titleMutableLiveData = MutableLiveData<String>()

    val textLongLiveData: LiveData<String> = textMutableLiveData
    val avatarLiveData: LiveData<String> = avatarMutableLiveData
    val titleLiveData: LiveData<String> = titleMutableLiveData

    fun setDetail(detail: Detail) {
        this.detail = detail
        textMutableLiveData.value = detail.text
        avatarMutableLiveData.value = detail.article!!.avatar
        titleMutableLiveData.value = detail.article!!.title
    }

    fun getDetail(): Detail {
        return detail
    }
}