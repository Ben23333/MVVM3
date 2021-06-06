package com.benone.mvvm3.bean

open class BaseBean {

    lateinit var responseType:String
    var responseCode:Int = 0
    lateinit var errorMessage:String
}