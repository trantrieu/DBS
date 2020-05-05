package com.dbs.list.adapter

internal data class ArticleModel(
    val id: Int,
    val title: String,
    val lastUpdate: String,
    val avatarUrl: String,
    val shortDescription: String
)