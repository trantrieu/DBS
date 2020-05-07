package com.dbs.article

import com.dbs.service.ServiceProvider
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ArticleProviderImpl @Inject constructor(
    private val serviceProvider: ServiceProvider
) : ArticleProvider {

    override fun fetchListArticle(): Single<ArticleListResult> {
        return serviceProvider.getArticles().map {
            val sortList = it.sortedByDescending { article -> article.lastUpdate }
            ArticleListResult.Success(sortList) as ArticleListResult
        }.onErrorReturn {
            ArticleListResult.Failure(it.message) as ArticleListResult
        }
    }

}