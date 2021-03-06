package com.dbs.detail

import dagger.Binds
import dagger.Module

@Module
internal abstract class DetailModule {

    @Binds
    abstract fun bindDetailProvider(detailProviderImpl: DetailProviderImpl): DetailProvider

}