package com.dbs.network

import android.content.Context
import com.dbs.config.HostConfig
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
internal interface NetworkComponent {

    fun provideRetrofit2() : Retrofit

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindsContext(context: Context): Builder

        @BindsInstance
        fun bindsHostConfig(hostConfig: HostConfig): Builder

        fun build(): NetworkComponent

    }
}