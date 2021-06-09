package com.benone.mvvm3.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.benone.mvvm3.ApplicationContext
import com.benone.mvvm3.R
import com.benone.mvvm3.bean.HomeBean
import com.benone.mvvm3.utils.loadNormalImage
import kotlinx.android.synthetic.main.recommendlistadapter_item.view.*

class RecommendListAdapter(private var dadaist:MutableList<HomeBean.IssueListBean.ItemListBean>):
RecyclerView.Adapter<RecommendListAdapter.MyViewHolder>(){

    class MyViewHolder(val binding: ViewDataBinding):RecyclerView.ViewHolder(binding.root)

    companion object{
        const val TAG = "RecommendListAdapter123"
    }

    lateinit var context:AppCompatActivity

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context as AppCompatActivity
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(context),
            R.layout.recommendlistadapter_item,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.setVariable(com.benone.mvvm3.BR.itemListBean,dadaist[position])
        holder.binding.executePendingBindings()
        val bean = dadaist[position]
        //播放器相关设置
        holder.binding.root.gsy_player_recommend_item.run{
            setUp(bean.data!!.playUrl,false,null,null)

            val imageView = ImageView(context)
            loadNormalImage(imageView,bean.data?.cover?.feed)

            thumbImageView = imageView
            thumbImageView.transitionName = ApplicationContext.getString(R.string.transitionName_video)
            setThumbPlay(true)
            titleTextView.text = bean.data!!.title
            //隐藏返回按钮
            backButton.visibility = View.GONE
            //设置全屏按键功能
            fullscreenButton.setOnClickListener {
                startWindowFullscreen(context,false,true)
            }
            //防止错位设置
            playTag = TAG
            playPosition = position
            //是否根据视频尺寸，自动选择竖屏全屏或者横屏全屏
            isAutoFullWithSize = true
            //音频焦点冲突时是否释放
            isReleaseWhenLossAudio = false
            //全屏动画
            isShowFullAnimation = true
            //小屏时不触摸滑动
            setIsTouchWiget(false)
        }
        holder.binding.root.setOnClickListener {
            topDetail(bean,holder,0)
        }
        holder.binding.root.tv_reply_recommend_item.setOnClickListener {
            topDetail(bean,holder,1)
        }
    }

    private fun topDetail(
        bean:HomeBean.IssueListBean.ItemListBean,
        holder:MyViewHolder,
        showPosition:Int
    ){
        //未完成
    }

    override fun getItemCount(): Int {
        return dadaist.size
    }

}