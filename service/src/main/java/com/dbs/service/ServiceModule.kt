package com.dbs.service

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
internal class ServiceModule {

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit) : Service {
        return retrofit.create(Service::class.java)
    }

    @Singleton
    @Provides
    fun provideServiceProviderInterface(serviceProviderImpl: ServiceProviderImpl): ServiceProvider {
        return serviceProviderImpl
    }
}