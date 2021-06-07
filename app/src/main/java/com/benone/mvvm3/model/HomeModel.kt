package com.benone.mvvm3.model

import com.benone.mvvm3.bean.BaseBean
import io.reactivex.Observable

class HomeModel : BaseModel() {

    var isLoadMore = false

    lateinit var date: String

    override fun getObservable(): Observable<out BaseBean> {

        return if (isLoadMore) getRestService().getHomeMoreData(
            date,
            "3"
        ) else getRestService().getHomeData()
    }

}