package com.benone.mvvm3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val recommendPosition = 0
    private val hotPosition = 0
    private val homeItem = 0
    private val hotItem = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentList:MutableList<Fragment> = mutableListOf(
            RecommendListFragment.newInstance(),
            HotListFragment.newInstance()
        )

        val adapter = CommenViewPagerAdapter(

        )
    }
}