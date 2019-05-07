package top.sogrey.yueji.utils

import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView
import android.view.animation.AnimationUtils

/**
 * 描述：RecyclerView 方法扩展
 * Created by Sogrey on 2018/12/11.
 */

fun RecyclerView.runLayoutAnimation(animResID:Int) {
    val context = this.context
    val controller = AnimationUtils.loadLayoutAnimation(context, animResID)

    this.layoutAnimation = controller
    this.adapter!!.notifyDataSetChanged()
    this.scheduleLayoutAnimation()
}

fun RecyclerView.setDefaultItemAnimator(){
    //设置默认动画
    val animator = DefaultItemAnimator()
    //设置动画时间
    animator.addDuration = 500
    animator.removeDuration = 500
    this.itemAnimator = animator
}