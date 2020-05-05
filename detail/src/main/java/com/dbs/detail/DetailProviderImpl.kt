package com.dbs.detail

import com.dbs.data.article.detail.Detail
import com.dbs.data.article.list.Article
import com.dbs.service.ServiceProvider
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class DetailProviderImpl @Inject constructor(
    private val serviceProvider: ServiceProvider
) : DetailProvider {

    override fun fetchDetailWithArticle(id: Int, articleList: List<Article>): Single<DetailResult> {
        return Single.zip(
            findArticleWithId(id, articleList),
            serviceProvider.getDetail(id),
            BiFunction<Article, Detail, DetailResult> { article, detail ->
                detail.article = article
                DetailResult.Success(detail) as DetailResult
            })
            .onErrorReturn {
                val msg = it.message ?: "Generic failure"
                DetailResult.Failure(msg)
            }
    }

    private fun findArticleWithId(id: Int, articleList: List<Article>): Single<Article> {
        return Observable.fromIterable(articleList).filter {
            it.id == id
        }.firstOrError()
    }
}