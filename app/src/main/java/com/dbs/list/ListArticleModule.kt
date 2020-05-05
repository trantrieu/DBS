package com.dbs.list

import com.dbs.MainScope
import com.dbs.article.ArticleProvider
import com.dbs.article.ArticleProviderObjectGraph
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

    @Provides
    fun provideCompositeDisposal(): CompositeDisposable {
        return CompositeDisposable()
    }

}