package com.dbs.data.article.list

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(
    val id: Int,
    val title: String,
    val lastUpdate: Long,
    val shortDescription: String,
    val avatar: String
) : Parcelable