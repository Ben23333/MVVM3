package com.benone.mvvm3.network.interceptors

import okhttp3.internal.platform.Platform

interface Logger {

    fun log(level: Int, tag: String, msg: String)

    val DEFAULT: Logger
        get() = object :Logger{
            override fun log(level: Int, tag: String, msg: String) {
                Platform.get().log(level, msg, null)
            }

        }

}