package com.dbs.service

import com.dbs.data.article.detail.Detail
import com.dbs.data.article.list.Article
import com.dbs.service.detail.DetailResponse
import com.dbs.service.article.ArticleResponse

internal object ResponseConverter {

    fun convertArticle(list: List<ArticleResponse>): List<Article> {
        return list.map {
            with(it) {
                Article(id, title, lastUpdate, shortDescription, avatar)
            }
        }
    }

    fun convertDetail(detail: DetailResponse): Detail {
        return with(detail) {
            Detail(id, text)
        }
    }

}