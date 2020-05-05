package com.dbs.article

import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal abstract class ArticleModule {

    @Binds
    abstract fun bindArticleProvider(articleProviderImpl: ArticleProviderImpl): ArticleProvider

}