package com.benone.mvvm3.interfaces

import com.benone.mvvm3.viewmodel.BaseViewModel

interface IBaseView {

    fun<T:BaseViewModel> createViewModel(viewModelClass:Class<T>):T

    fun registerViewModelObserver(baseViewModel: BaseViewModel)

    fun onApiSuccessCallBack(any:Any)

    fun onApiErrorCallBack(any: Any)
}