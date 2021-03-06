package com.benone.mvvm3.customView

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class ViewpagerScroll(context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {

    var isScroll = false

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return if(isScroll){
            super.onInterceptTouchEvent(ev)
        } else{
            false
        }
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return if(isScroll){
            super.onTouchEvent(ev)
        }else{
            true
        }
    }

    override fun setCurrentItem(item: Int) {
        super.setCurrentItem(item,false)
    }
}