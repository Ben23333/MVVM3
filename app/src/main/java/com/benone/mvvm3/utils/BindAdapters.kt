package com.benone.mvvm3.utils

import android.widget.ImageView
import java.text.SimpleDateFormat
import java.util.*

fun loadNormalImage(imageView: ImageView,url:String?){
    ImageLoaderUtil.getInstance().
}

fun String.getCustomTime(time:Long):String{
    val date = Date()
    date.time = java.lang.Long.parseLong(time.toString())

    val template = SimpleDateFormat("dd/MM HH:mm")
    return template.format(date)

}