package com.benone.mvvm3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.benone.mvvm3.R
import com.benone.mvvm3.bean.HomeBean
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

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.setVariable(com.benone.mvvm3.BR.itemListBean,dadaist[position])
        holder.binding.executePendingBindings()
        val bean = dadaist[position]
        //播放器相关设置
        holder.binding.root.gsy_player_recommend_item.run{
            setUp(bean.data!!.playUrl,false,null,null)

            val imageView = ImageView(context)
            loadNormalImage()
        }
    }

    override fun getItemCount(): Int {
        return dadaist.size
    }

}