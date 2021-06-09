package com.benone.mvvm3.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.benone.mvvm3.BaseFragment
import com.benone.mvvm3.R
import com.benone.mvvm3.adapter.RecommendListAdapter
import com.benone.mvvm3.viewmodel.HomeViewModel
import com.shuyu.gsyvideoplayer.GSYVideoManager
import kotlinx.android.synthetic.main.fragment_recommend_list.*

/**
 * 推荐列表
 */
class RecommendListFragment : BaseFragment() {

    private lateinit var adapter: RecommendListAdapter
    private var homeViewModel: HomeViewModel? = null
    private lateinit var refresh_recommend1: SwipeRefreshLayout
    private lateinit var rv_list_recommend1: RecyclerView

    companion object {
        fun newInstance() = RecommendListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_recommend_list, container, false)
        refresh_recommend1 = refresh_recommend
        rv_list_recommend1 = rv_list_recommend
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        refresh_recommend1.setOnRefreshListener {
            homeViewModel?.loadData(false)
        }

        rv_list_recommend1.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager: LinearLayoutManager =
                    recyclerView.layoutManager as LinearLayoutManager
                val lastPosition = layoutManager.findLastVisibleItemPosition()
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastPosition == homeViewModel!!.getDatas().size - 1) {
                    homeViewModel?.loadData(true)
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager
                if (layoutManager is LinearLayoutManager && GSYVideoManager.instance().playPosition >= 0) {
                    val lastItemPosition = layoutManager.findLastVisibleItemPosition()
                    val firstItemPosition = layoutManager.findFirstVisibleItemPosition()
                    val visibleitemCount = layoutManager.childCount
                    if (GSYVideoManager.instance().playPosition >= 0) {
                        val position = GSYVideoManager.instance().playPosition
                        if (GSYVideoManager.instance().playTag == RecommendListAdapter.TAG &&
                            (position < firstItemPosition || position > lastItemPosition)
                        ) {
                            if (GSYVideoManager.isFullState(activity)) {
                                return
                            }
                            GSYVideoManager.releaseAllVideos()
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        })
    }

    override fun onPause() {
        super.onPause()
        GSYVideoManager.onPause()
    }

    override fun onResume() {
        super.onResume()
        if (homeViewModel == null) {
            homeViewModel == createViewModel(HomeViewModel::class.java)
            registerViewModelObserver(homeViewModel!!)
            adapter = RecommendListAdapter(homeViewModel!!.getDatas())
            rv_list_recommend1.layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL, false
            )
            rv_list_recommend1.adapter = adapter
            homeViewModel!!.loadData(false)
        }
        GSYVideoManager.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoManager.releaseAllVideos()
    }

    override fun onApiSuccessCallBack(any: Any) {
        super.onApiSuccessCallBack(any)
        refresh_recommend1.isRefreshing = false
        adapter.notifyDataSetChanged()
    }

    override fun onApiErrorCallBack(any: Any) {
        super.onApiErrorCallBack(any)
        refresh_recommend1.isRefreshing = false
        Toast.makeText(activity, any.toString(), Toast.LENGTH_SHORT).show()
    }

}