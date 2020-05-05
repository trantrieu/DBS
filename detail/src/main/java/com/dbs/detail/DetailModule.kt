package com.dbs.detail

import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal abstract class DetailModule {

    @Binds
    abstract fun bindDetailProvider(detailProviderImpl: DetailProviderImpl): DetailProvider

}