package com.dbs

import android.content.Context
import com.dbs.article.ArticleProvider
import com.dbs.article.ArticleProviderObjectGraph
import com.dbs.config.SchedulerConfig
import com.dbs.network.NetworkObjectGraph
import com.dbs.service.ServiceObjectGraph
import com.dbs.service.ServiceProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
internal class DBSAppModule {

    @Singleton
    @Provides
    fun provideService(context: Context): ServiceProvider {
        val network = NetworkObjectGraph(context)
        return ServiceObjectGraph(network.retrofit()).provideServiceProvider()
    }

    @Singleton
    @Provides
    fun provideSchedulerConfig(): SchedulerConfig {
        return SchedulerConfig.getDefaultSchedulerConfig()
    }
}