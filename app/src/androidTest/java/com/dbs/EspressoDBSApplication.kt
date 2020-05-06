package com.dbs

class EspressoDBSApplication : DBSApp() {

    override fun onCreate() {
        super.onCreate()
        dbsAppComponent = DaggerEspressoDBSAppComponent
            .builder()
            .bindsContext(this)
            .build()
    }
}