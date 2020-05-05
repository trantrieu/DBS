package com.dbs.data.article.list

data class Article(
    val id: Int,
    val title: String,
    val lastUpdate: Long,
    val shortDescription: String,
    val avatar: String
)