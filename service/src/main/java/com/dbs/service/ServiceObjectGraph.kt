package com.dbs.service

import retrofit2.Retrofit

class ServiceObjectGraph (retrofit: Retrofit){

    private var component = DaggerServiceComponent.builder()
        .bindRetrofit2(retrofit)
        .build()

    fun provideServiceProvider(): ServiceProvider {
        return component.provideServiceProvider()
    }
}