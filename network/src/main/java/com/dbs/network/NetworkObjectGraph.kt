package com.dbs.network

import android.content.Context
import com.dbs.config.HostConfig
import retrofit2.Retrofit

class NetworkObjectGraph(context: Context, hostConfig: HostConfig) {

    private var component = DaggerNetworkComponent
        .builder()
        .bindsContext(context)
        .bindsHostConfig(hostConfig)
        .build()

    fun retrofit() : Retrofit {
        return component.provideRetrofit2()
    }

}