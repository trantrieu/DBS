package com.dbs.detail

import com.dbs.data.article.list.Article
import io.reactivex.Single

interface DetailProvider {

    fun fetchDetailWithArticle(id: Int, articleList: List<Article>): Single<DetailResult>

}