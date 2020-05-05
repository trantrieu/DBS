package com.dbs.detail

import com.dbs.service.ServiceProvider

class DetailProviderObjectGraph(serviceProvider: ServiceProvider) {

    private val component = DaggerDetailProviderComponent.builder()
        .bindsServiceProvider(serviceProvider)
        .build()

    fun getDetailProvider(): DetailProvider {
        return component.provideDetailProvider()
    }
}