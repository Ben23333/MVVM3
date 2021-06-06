package com.benone.mvvm3.utils

import java.text.SimpleDateFormat
import java.util.*

fun String.getCustomTime(time:Long):String{
    val date = Date()
    date.time = java.lang.Long.parseLong(time.toString())

    val template = SimpleDateFormat("dd/MM HH:mm")
    return template.format(date)

}