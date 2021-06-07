package com.benone.mvvm3.model

import com.benone.mvvm3.bean.BaseBean
import com.benone.mvvm3.interfaces.IDatasListener
import com.benone.mvvm3.network.RestCreator
import com.benone.mvvm3.network.RestService
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class BaseModel {

    private var iDatasListener:IDatasListener? = null


    abstract fun getObservable():Observable<out BaseBean>

    protected fun getRestService():RestService{
        return RestCreator.getInstance().getRestService()
    }

    fun loadData(){
        getObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object:Observer<BaseBean>{
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: BaseBean) {
                    t.responseCode=1
                    t.responseType = ""
                    iDatasListener?.getSuccess(t)
                }

                override fun onError(e: Throwable) {
                    val baseBean = BaseBean()
                    baseBean.responseType = ""
                    baseBean.responseCode = -1
                    baseBean.errorMessage = e.message.toString()
                    iDatasListener?.getFailed(baseBean)
                }

                override fun onComplete() {

                }

            })
    }

    fun setIDataListener(iDataListener: IDatasListener){
        this.iDatasListener = iDataListener
    }
}