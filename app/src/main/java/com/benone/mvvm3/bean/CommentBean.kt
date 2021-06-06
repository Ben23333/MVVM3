package com.benone.mvvm3.bean

import com.benone.mvvm3.utils.getCustomTime

data class CommentBean(
    var replyList: List<ReplyListBean>?,
    var count: Int,
    var total: Int,
    var nextPageUrl: String?
) : BaseBean() {

    data class ReplyListBean(
        var sequence:Int,
        var message:String?,
        var createTime:Long,
        var user:UserBean?,
        var likeCount:String?
    ){
        fun getTime():String{
            return String().getCustomTime(createTime)
        }

        fun isShowLikeCount():Boolean{
            return !likeCount.equals("0")
        }

        data class UserBean(
            var uid:Long,
            var nickname:String?,
            var avata:String?
        )
    }
}