package com.benone.mvvm3

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.benone.mvvm3.interfaces.IBaseView
import com.benone.mvvm3.viewmodel.BaseViewModel

open class BaseFragment: Fragment(), IBaseView {
    override fun <T : BaseViewModel> createViewModel(viewModelClass: Class<T>): T {
        return ViewModelProvider(this).get(viewModelClass)
    }

    override fun registerViewModelObserver(baseViewModel: BaseViewModel) {
        baseViewModel.getSuccessLiveData().observe(this,Observer<Any>{
            if(it!=null){
                onApiSuccessCallBack(it)
            }
        })

        baseViewModel.getErrorLiveData().observe(this){
            if(it!=null){
                onApiErrorCallBack(it)
            }
        }
    }

    override fun onApiSuccessCallBack(any: Any) {
    }

    override fun onApiErrorCallBack(any: Any) {
    }
}