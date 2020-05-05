package com.dbs.service

import com.dbs.data.article.list.Article
import com.dbs.service.titles.ArticleResponse

internal object ResponseConverter {

    fun convertArticle(list: List<ArticleResponse>): List<Article> {
        return list.map {
            with(it) {
                Article(id, title, lastUpdate, shortDescription, avatar)
            }
        }
    }

}