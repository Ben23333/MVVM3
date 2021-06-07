package com.benone.mvvm3.network

import com.benone.mvvm3.BuildConfig
import com.benone.mvvm3.network.interceptors.LoggingInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class RestCreator {

    private var okHttpClient:OkHttpClient = OkHttpClient.Builder()
        .retryOnConnectionFailure(false)
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .addInterceptor(
            LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .setLevel(Level.BASIC)
        )
        .build()
}