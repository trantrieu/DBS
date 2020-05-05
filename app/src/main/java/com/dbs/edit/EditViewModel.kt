package com.dbs.edit

import androidx.lifecycle.ViewModel
import com.dbs.data.article.detail.Detail

internal class EditViewModel : ViewModel() {

    lateinit var detail: Detail

    fun updateText(text: String) {
        detail.text = text
    }
}