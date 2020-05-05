package com.dbs.article

import com.dbs.data.article.list.Article
import com.dbs.service.ServiceProvider
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ArticleProviderImpl @Inject constructor(
    private val serviceProvider: ServiceProvider
) : ArticleProvider {

    override fun fetchListArticle(): Single<ArticleListResult> {
        return serviceProvider.getArticles().map {
            val sortList = it.sortedBy { article ->
                article.lastUpdate
            }
            ArticleListResult.Success(sortList) as ArticleListResult
        }.onErrorReturn {
            val msg = it.message ?: "Generic failure"
            ArticleListResult.Failure(msg) as ArticleListResult
        }
    }

}