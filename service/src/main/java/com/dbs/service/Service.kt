package com.dbs.service

import com.dbs.service.titles.ArticleResponse
import io.reactivex.Single
import retrofit2.http.GET

internal interface Service {

    @GET("/article")
    fun getArticles(): Single<List<ArticleResponse>>
}