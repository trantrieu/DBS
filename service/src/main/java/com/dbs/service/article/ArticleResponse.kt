package com.dbs.service.article

import com.google.gson.annotations.SerializedName

internal data class ArticleResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("last_update")
    val lastUpdate: Long,
    @SerializedName("short_description")
    val shortDescription: String,
    @SerializedName("avatar")
    val avatar: String
)