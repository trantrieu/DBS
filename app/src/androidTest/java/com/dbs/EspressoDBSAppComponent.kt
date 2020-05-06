package com.dbs

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [EspressoDBSAppModule::class])
internal interface EspressoDBSAppComponent : DBSAppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindsContext(context: Context): Builder

        fun build(): EspressoDBSAppComponent

    }

}