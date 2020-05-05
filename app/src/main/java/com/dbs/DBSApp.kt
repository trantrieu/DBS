package com.dbs.list

import android.app.Application
import android.content.Context
import com.dbs.DBSAppComponent
import com.dbs.DaggerDBSAppComponent

class DBSApp : Application() {

    private lateinit var dbsAppComponent: DBSAppComponent

    override fun onCreate() {
        super.onCreate()
        dbsAppComponent = DaggerDBSAppComponent.builder()
            .bindsContext(this)
            .build()
    }

    companion object {
        fun getApp(context: Context): DBSAppComponent {
            return (context.applicationContext as DBSApp).dbsAppComponent
        }
    }
}