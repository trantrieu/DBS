package com.dbs

import android.content.Context
import com.dbs.config.HostConfig
import com.dbs.config.SchedulerConfig
import com.dbs.network.NetworkObjectGraph
import com.dbs.service.ServiceObjectGraph
import com.dbs.service.ServiceProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class EspressoDBSAppModule {

    @Singleton
    @Provides
    fun provideService(context: Context, hostConfig: HostConfig): ServiceProvider {
        val network = NetworkObjectGraph(context, hostConfig)
        return ServiceObjectGraph(network.retrofit()).provideServiceProvider()
    }

    @Singleton
    @Provides
    fun provideSchedulerConfig(): SchedulerConfig {
        return SchedulerConfig.getDefaultSchedulerConfig()
    }

    @Singleton
    @Provides
    fun provideHostConfig(): HostConfig {
        return object : HostConfig {
            override fun getHost(): String {
                return "http://127.0.0.1:8080"
            }
        }
    }

}