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
internal class DBSAppModule {

    @Singleton
    @Provides
    fun provideHostConfig(): HostConfig {
        return HostConfig.getDefaultHostConfig()
    }

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
}