package com.dbs.service

import com.dbs.data.article.detail.Detail
import com.dbs.data.article.list.Article
import io.reactivex.Single

interface ServiceProvider {

    fun getArticles(): Single<List<Article>>

    fun getDetail(id: Int): Single<Detail>
}