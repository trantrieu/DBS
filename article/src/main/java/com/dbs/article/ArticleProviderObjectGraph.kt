package com.dbs.article

import com.dbs.service.ServiceProvider

class ArticleProviderObjectGraph(serviceProvider: ServiceProvider) {

    private val component = DaggerArticleProviderComponent.builder()
        .bindsServiceProvider(serviceProvider)
        .build()

    fun getArticlesProvider(): ArticleProvider {
        return component.provideArticleProvider()
    }
}