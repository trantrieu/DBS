package com.dbs.service

import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [ServiceModule::class])
internal interface ServiceComponent {

    fun provideServiceProvider(): ServiceProvider

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindRetrofit2(retrofit: Retrofit): Builder

        fun build(): ServiceComponent
    }

}