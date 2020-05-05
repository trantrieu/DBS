package com.dbs.detail

import com.dbs.data.article.detail.Detail

sealed class DetailResult {

    data class Success(val detail: Detail) : DetailResult()

    data class Failure(val message: String) : DetailResult()
}