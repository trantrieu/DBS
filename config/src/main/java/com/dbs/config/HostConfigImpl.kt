package com.dbs.config

internal class HostConfigImpl : HostConfig{

    override fun getHost(): String {
        return BuildConfig.HOST
    }

}