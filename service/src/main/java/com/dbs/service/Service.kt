package com.dbs.service

import com.dbs.service.detail.DetailResponse
import com.dbs.service.article.ArticleResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

internal interface Service {

    @GET("/article")
    fun getArticles(): Single<List<ArticleResponse>>

    @GET("/article/{id}")
    fun getDetail(@Path("id") id: Int): Single<DetailResponse>

}