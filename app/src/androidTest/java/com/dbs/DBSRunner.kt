package com.dbs

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.squareup.rx2.idler.Rx2Idler
import io.reactivex.plugins.RxJavaPlugins

class DBSRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, EspressoDBSApplication::class.java.getName(), context)
    }

    override fun onStart() {
        super.onStart()
        RxJavaPlugins.setInitIoSchedulerHandler(
            Rx2Idler.create("RxJava 2.x IOS Scheduler")
        );
    }
}