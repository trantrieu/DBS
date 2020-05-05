package com.dbs.detail

import com.dbs.service.ServiceProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DetailModule::class])
internal interface DetailProviderComponent {

    fun provideDetailProvider(): DetailProvider

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindsServiceProvider(serviceProvider: ServiceProvider): Builder

        fun build(): DetailProviderComponent

    }
}