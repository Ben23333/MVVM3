package com.benone.mvvm3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.benone.mvvm3.home.RecommendListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val recommendPosition = 0
    private val hotPosition = 1
    private val homeItem = 0
    private val hotItem = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentList:MutableList<Fragment> = mutableListOf(
            RecommendListFragment.newInstance(),
//            HotListFragment.newInstance()
        )

//        val adapter = CommenViewPagerAdapter()
        vp_content_mainactivity.offscreenPageLimit = fragmentList.size
//        vp_content_mainactivity.adapter = adapter
        vp_content_mainactivity.currentItem = recommendPosition
        vp_content_mainactivity.isScroll=true
        vp_content_mainactivity.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
               setBottomButton(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })

        btn_recommend_mainacitivity.setOnClickListener(this)
        btn_hot_mainacitivity.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_recommend_mainacitivity->{
                setBottomButton(homeItem)
            }
            R.id.btn_hot_mainacitivity->{
                setBottomButton(hotItem)
            }
        }
    }

    private fun setBottomButton(item:Int){
        when(item){
            homeItem->{
                btn_recommend_mainacitivity.setTextColor(ContextCompat.getColor(this,R.color.text_333))
                btn_hot_mainacitivity.setTextColor(ContextCompat.getColor(this,R.color.text_666))
                vp_content_mainactivity.currentItem=recommendPosition
            }
            hotItem->{
                btn_recommend_mainacitivity.setTextColor(ContextCompat.getColor(this,R.color.text_666))
                btn_hot_mainacitivity.setTextColor(ContextCompat.getColor(this,R.color.text_333))
                vp_content_mainactivity.currentItem=hotPosition
            }
        }
    }
}