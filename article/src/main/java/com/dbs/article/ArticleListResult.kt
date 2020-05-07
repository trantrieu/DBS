package com.dbs.article

import com.dbs.data.article.list.Article

sealed class ArticleListResult {

    data class Success(val articleList: List<Article>) : ArticleListResult()

    data class Failure(val message: String?) : ArticleListResult()
}