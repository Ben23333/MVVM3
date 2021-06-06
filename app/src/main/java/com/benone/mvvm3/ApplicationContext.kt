package com.benone.mvvm3

import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.annotation.*
import androidx.core.content.ContextCompat

object ApplicationContext {

    private lateinit var context:Context
    private lateinit var uiHandle:Handler

    @RequiresApi(Build.VERSION_CODES.P)
    fun initContext(context: Context){
        this.context = context
        this.uiHandle = Handler.createAsync(Looper.getMainLooper())
    }

    fun getContext():Context = context.applicationContext

    fun getUiHandler() = uiHandle

    fun getString(@StringRes resId:Int)=context.getString(resId)

    fun getString(@StringRes resId: Int,vararg formatArgs:Object) = context.getString(resId,formatArgs)

    fun getColor(@ColorRes resId: Int):Int = ContextCompat.getColor(context,resId)

    fun getDimensionPixelOffset(@DimenRes resId: Int):Int = context.resources.getDimensionPixelOffset(resId)

    fun getDrawable(@DrawableRes resId: Int) = ContextCompat.getDrawable(context,resId)

    fun getIdentifier(name:String,defType:String,defPackage:String):Int{
        return context.resources.getIdentifier(name,defType,defPackage)
    }

    fun getSystemId(name:String):Int = getIdentifier(name,"id","android")


}