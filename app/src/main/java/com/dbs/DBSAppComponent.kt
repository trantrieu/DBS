package com.dbs

import android.content.Context
import com.dbs.config.SchedulerConfig
import com.dbs.service.ServiceProvider
import dagger.BindsInstance
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [DBSAppModule::class])
interface DBSAppComponent {

    fun provideService(): ServiceProvider

    fun provideSchedulerConfig(): SchedulerConfig

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindsContext(context: Context): Builder

        fun build(): DBSAppComponent

    }
}