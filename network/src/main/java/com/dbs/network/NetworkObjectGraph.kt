package com.dbs.network

import android.content.Context
import retrofit2.Retrofit

class NetworkObjectGraph(context: Context) {

    private var component = DaggerNetworkComponent
        .builder()
        .bindsContext(context)
        .build()

    fun retrofit() : Retrofit {
        return component.provideRetrofit2()
    }

}