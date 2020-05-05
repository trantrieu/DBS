package com.dbs.data.article.detail

import android.os.Parcelable
import com.dbs.data.article.list.Article
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Detail(val id: Int, var text: String, var article: Article? = null) : Parcelable