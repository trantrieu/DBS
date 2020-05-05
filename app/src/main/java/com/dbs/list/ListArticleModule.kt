package com.dbs.list

import com.dbs.MainScope
import com.dbs.article.ArticleProvider
import com.dbs.article.ArticleProviderObjectGraph
import com.dbs.detail.DetailProvider
import com.dbs.detail.DetailProviderObjectGraph
import com.dbs.service.ServiceProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
internal class ListArticleModule {

    @MainScope
    @Provides
    fun getArticleProvider(serviceProvider: ServiceProvider): ArticleProvider {
        val articleProviderObjectGraph = ArticleProviderObjectGraph(serviceProvider)
        return articleProviderObjectGraph.getArticlesProvider()
    }

    @MainScope
    @Provides
    fun getDetailProvider(serviceProvider: ServiceProvider): DetailProvider {
        val detailProviderObjectGraph = DetailProviderObjectGraph(serviceProvider)
        return detailProviderObjectGraph.getDetailProvider()
    }

    @Provides
    fun provideCompositeDisposal(): CompositeDisposable {
        return CompositeDisposable()
    }

}