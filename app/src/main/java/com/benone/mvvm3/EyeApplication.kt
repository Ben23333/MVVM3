package com.benone.mvvm3

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi

class EyeApplication:Application() {

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate() {
        super.onCreate()
        ApplicationContext.initContext(this)
    }
}