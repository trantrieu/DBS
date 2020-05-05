package com.dbs.config

import io.reactivex.Scheduler

interface SchedulerConfig {

    fun getMainScheduler(): Scheduler

    fun getIOScheduler(): Scheduler

    companion object {
        fun getDefaultSchedulerConfig(): SchedulerConfig {
            return SchedulerConfigImpl()
        }
    }
}