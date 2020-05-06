package com.dbs.network

import android.content.Context
import com.dbs.config.HostConfig
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
internal class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit2(context: Context, okHttpClient: OkHttpClient, hostConfig: HostConfig) : Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(hostConfig.getHost())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(NetworkExceptionAdapterFactory(context))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttp() : OkHttpClient {

        val okHttpLog = HttpLoggingInterceptor()
        okHttpLog.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(okHttpLog)
            .build()
    }
}