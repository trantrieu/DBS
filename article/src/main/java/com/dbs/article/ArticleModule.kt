package com.dbs.article

import dagger.Binds
import dagger.Module

@Module
internal abstract class ArticleModule {

    @Binds
    abstract fun bindArticleProvider(articleProviderImpl: ArticleProviderImpl): ArticleProvider

}