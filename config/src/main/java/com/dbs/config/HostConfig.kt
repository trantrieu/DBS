package com.dbs.config

interface HostConfig {

    fun getHost(): String

    companion object {
        fun getDefaultHostConfig(): HostConfig = HostConfigImpl()
    }

}