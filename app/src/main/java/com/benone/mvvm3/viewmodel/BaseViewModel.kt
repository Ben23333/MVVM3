package com.benone.mvvm3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.benone.mvvm3.bean.BaseBean
import com.benone.mvvm3.interfaces.IDatasListener
import com.benone.mvvm3.model.BaseModel

open class BaseViewModel:ViewModel(),IDatasListener {

    var baseModel:BaseModel?=null
    private val successLiveData = MutableLiveData<Any>()
    private val errorLiveData = MutableLiveData<Any>()

    fun getSuccessLiveData():MutableLiveData<Any> = successLiveData
    fun getErrorLiveData():MutableLiveData<Any> = errorLiveData

    open fun onSuccess(any:Any){
        successLiveData.value = any
    }

    open fun onFail(any:Any){
        errorLiveData.value =any
    }

    open fun loadData(){
        baseModel?.let{
            it.setIDataListener(this)
            it.loadData()

        }
    }

    override fun getSuccess(baseBean: BaseBean) {
        onSuccess(baseBean)
    }

    override fun getFailed(baseBean: BaseBean) {
        onFail(baseBean)
    }

}