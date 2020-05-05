package com.dbs.article

import com.dbs.service.ServiceProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ArticleModule::class])
internal interface ArticleProviderComponent {

    fun provideArticleProvider(): ArticleProvider

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindsServiceProvider(serviceProvider: ServiceProvider): Builder

        fun build(): ArticleProviderComponent

    }
}