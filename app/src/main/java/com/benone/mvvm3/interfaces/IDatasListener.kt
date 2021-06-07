package com.benone.mvvm3.interfaces

import com.benone.mvvm3.bean.BaseBean

interface IDatasListener {

    fun getSuccess(baseBean: BaseBean)

    fun getFailed(baseBean: BaseBean)
}