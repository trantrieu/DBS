package com.dbs.article

import io.reactivex.Single

interface ArticleProvider {

    fun fetchListArticle(): Single<ArticleListResult>

}