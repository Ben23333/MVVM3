package com.benone.mvvm3.network


import com.benone.mvvm3.BuildConfig
import com.benone.mvvm3.network.interceptors.Level
import com.benone.mvvm3.network.interceptors.LoggingInterceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
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
                .log(Platform.INFO)
                .request("Request")
                .response("Response")
                .build()
        )
        .build()

    private var retrofit:Retrofit = Retrofit.Builder()
        .baseUrl(RestService.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    companion object{
        @Volatile
        private var instance:RestCreator? = null

        fun getInstance():RestCreator{
            if(instance==null){
                synchronized(RestCreator::class.java){
                    if(instance==null){
                        instance = RestCreator()
                    }
                }
            }
            return instance!!
        }
    }

    fun getRestService():RestService{
        return retrofit.create(RestService::class.java)
    }
}