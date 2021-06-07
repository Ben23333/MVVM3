package com.benone.mvvm3.network.interceptors


import android.text.TextUtils
import android.util.Log
import okhttp3.*
import okhttp3.internal.platform.Platform
import java.util.concurrent.TimeUnit

class LoggingInterceptor(builder: Builder) : Interceptor {

    private var builder: Builder = builder
    private var isDebug: Boolean = builder.isDebug

    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        if (builder.getHeaders().size() > 0) {
            val headers: Headers = request.headers()
            val names: Set<String> = headers.names()
            val iterator: Iterator<String> = names.iterator()
            val requestBuilder: Request.Builder = request.newBuilder()
            requestBuilder.headers(builder.getHeaders())
            while (iterator.hasNext()) {
                val name: String = iterator.next()
                requestBuilder.addHeader(name, headers.get(name))
            }
            request = requestBuilder.build()
        }
        Log.d("interceptintercept0", "${request.url()}\nhttpCode:${request.body()}")

        if (!isDebug || builder.getLevel() == Level.NONE) {
            return chain.proceed(request)
        }

        val requestBody: RequestBody? = request.body()

        var rContentType: MediaType? = null
        if (requestBody != null) {
            rContentType = requestBody.contentType()
        }

        var rSubtype: String? = null
        if (rContentType != null) {
            rSubtype = rContentType.subtype()
        }

        if (rSubtype != null && (rSubtype.contains("json")
                    || rSubtype.contains("xml")
                    || rSubtype.contains("plain")
                    ||rSubtype.contains("html"))
        ){
            Printer.printJsonRequest(builder, request)
        }else{
            Printer.printFileRequest(builder, request)
        }

        val st:Long = System.nanoTime()
        val response:Response = chain.proceed(request)

        Log.d("interceptintercept1", "${response.request().url()}httpCode:${response.code()}")

        val segmentList:List<String> = (request.tag() as Request).url().encodedPathSegments()
        val chainMs:Long = TimeUnit.NANOSECONDS.toMillis(System.nanoTime()-st)
        val header:String = response.headers().toString()


    }

    class Builder {

        private var TAG: String = "LoggingI"
        internal var isDebug: Boolean = false
        private var type: Int = Platform.INFO
        private var requestTag: String = ""
        private var responseTag: String = ""
        private var level: Level = Level.BASIC
        private var builder: Headers.Builder = Headers.Builder()
        private var logger: Logger? = null


        fun getType(): Int = type

        fun getLevel() = level

        fun getHeaders(): Headers = builder.build()

        fun getTag(isRequest: Boolean): String {
            return if (isRequest) {
                if (TextUtils.isEmpty(requestTag)) TAG else requestTag
            } else {
                if (TextUtils.isEmpty(responseTag)) TAG else responseTag
            }
        }

        fun getLogger(): Logger? = logger

        fun addHeader(name: String, value: String): Builder {
            builder.set(name, value)
            return this
        }

        fun setLevel(level: Level): Builder {
            this.level = level
            return this
        }

        fun tag(tag: String): Builder {
            TAG = tag
            return this
        }

        fun request(tag: String): Builder {
            this.requestTag = tag
            return this
        }

        fun response(tag: String): Builder {
            this.responseTag = tag
            return this
        }

        fun loggable(isDebug: Boolean): Builder {
            this.isDebug = isDebug
            return this
        }

        fun log(type: Int): Builder {
            this.type = type
            return this
        }

        fun logger(logger: Logger): Builder {
            this.logger = logger
            return this
        }

        fun build(): LoggingInterceptor = LoggingInterceptor(this)


    }


}