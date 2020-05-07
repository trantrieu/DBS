package com.dbs.network

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import com.dbs.data.exception.NoConnectionException
import io.reactivex.Single
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.Type

internal class NetworkExceptionAdapterFactory(private val context: Context) : CallAdapter.Factory() {

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        val previousCallAdapter: CallAdapter<Any, Single<Any>> =
            retrofit.nextCallAdapter(this, returnType, annotations) as CallAdapter<Any, Single<Any>>
        return object : CallAdapter<Any, Any> {
            override fun adapt(call: Call<Any>): Any {
                return previousCallAdapter.adapt(call).onErrorResumeNext {
                    return@onErrorResumeNext if (!isConnected()) {
                        Single.error(NoConnectionException(context.getString(R.string.no_connection)))
                    } else {
                        Single.error(it)
                    }
                }
            }

            override fun responseType(): Type {
                return previousCallAdapter.responseType()
            }

        }
    }

    private fun isConnected(): Boolean {
        val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager?
        return cm?.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected
    }

}