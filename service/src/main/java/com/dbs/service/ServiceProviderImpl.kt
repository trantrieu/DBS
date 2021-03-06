package com.dbs.service

import com.dbs.data.article.detail.Detail
import com.dbs.data.article.list.Article
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ServiceProviderImpl @Inject constructor(private val service: Service) :
    ServiceProvider {

    override fun getArticles(): Single<List<Article>> {
        return service.getArticles().map { list ->
            ResponseConverter.convertArticle(list)
        }
    }

    override fun getDetail(id: Int): Single<Detail> {
        return service.getDetail(id).map { detail ->
            ResponseConverter.convertDetail(detail)
        }
    }

}