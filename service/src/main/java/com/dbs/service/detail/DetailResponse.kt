package com.dbs.service.detail

import com.google.gson.annotations.SerializedName

internal data class DetailResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("text")
    val text: String
)