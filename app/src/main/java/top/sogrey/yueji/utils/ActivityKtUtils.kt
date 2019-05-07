package top.sogrey.yueji.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import top.sogrey.yueji.R


/**
 * 描述：Activity 扩展
 * Created by Sogrey on 2018/12/5.
 */

/**
 * Activity 获取所有子孙View
 */
fun Activity.getAllChildViews(): List<View> {
    return window.decorView.getAllChildViews()
}

fun Activity.getIntentContainsKey(key: String): Boolean = intent!!.hasExtra(key)

/***************************************************
 * getBundle()
 */
fun Activity.getBundle(): Bundle? = intent!!.extras

fun Activity.getBundleContainsKey(key: String): Boolean = getBundle()!!.containsKey(key)

fun Activity.getBundleValueByKey(key: String): Any? = if (getBundleContainsKey(key)) {
    getBundle()!!.get(key)
} else {
    null
}

/***************************************************
 * finish
 */
fun Activity.finishThis() {
    finish()
    overridePendingTransition(R.anim.base_slide_remain, R.anim.base_slide_right_out)
}

fun Activity.finishThisDelay() {
    Handler().postDelayed({ this.finishThis() }, 1000)
}

fun Activity.finishThisRemain() {
    finish()
    overridePendingTransition(R.anim.base_slide_remain, R.anim.base_slide_remain)
}

fun Activity.finishThisRemainDelay() {
    Handler().postDelayed({ this.finishThisRemain() }, 1000)
}

/******************************************************
 * exit APP
 */
/**
 * 退出应用 - 真退出
 */
fun Activity.exitProgrames() {
    val startMain = Intent(Intent.ACTION_MAIN)
    startMain.addCategory(Intent.CATEGORY_HOME)
    startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    startActivity(startMain)
    android.os.Process.killProcess(android.os.Process.myPid())
}

/**
 * 退出应用 - 假退出
 */
fun Activity.exit2Home() {
    val i = Intent(Intent.ACTION_MAIN)
    i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    i.addCategory(Intent.CATEGORY_HOME)
    startActivity(i)
    finish()
}

/***********************************************************
 * startIntent
 */
const val DELAY = 0

/**
 * Activity 跳转
 *
 * @param cls 要跳转到的Activity
 */
fun Activity.startIntent(cls: Class<*>) {
    startIntent(cls, 0, 0, false)
}

fun Activity.startIntent(cls: Class<*>, isFinish: Boolean) {
    startIntent(cls, 0, 0, isFinish)
}

/**
 * Activity 跳转
 *
 * @param cls   要跳转到的Activity
 * @param delay 自定義延時（毫秒）
 */
fun Activity.startIntent(cls: Class<*>, delay: Int) {
    startIntent(cls, 0, 0, delay, false)
}

fun Activity.startIntent(cls: Class<*>, delay: Int, isFinish: Boolean) {
    startIntent(cls, 0, 0, delay, isFinish)
}

/**
 * Activity 跳转(动画)
 *
 * @param cls     要跳转到的Activity
 * @param animIn  进入动画
 * @param animOut 退出动画
 */
fun Activity.startIntent(cls: Class<*>, animIn: Int, animOut: Int) {
    startIntent(cls, animIn, animOut, -1, false)
}

fun Activity.startIntent(cls: Class<*>, animIn: Int, animOut: Int, isFinish: Boolean) {
    startIntent(cls, animIn, animOut, -1, isFinish)
}

/**
 * Activity 跳转(动画)
 *
 * @param cls     要跳转到的Activity
 * @param animIn  进入动画
 * @param animOut 退出动画
 * @param delay   自定義延時（毫秒）
 */
fun Activity.startIntent(cls: Class<*>, animIn: Int, animOut: Int, delay: Int) {
    startIntent(cls, null, animIn, animOut, delay, false)
}

fun Activity.startIntent(
        cls: Class<*>, animIn: Int, animOut: Int, delay: Int,
        isFinish: Boolean
) {
    startIntent(cls, null, animIn, animOut, delay, isFinish)
}

/**
 * Activity 跳转(动画)
 *
 * @param cls     要跳转到的Activity
 * @param bundle  传递参数
 * @param animIn  进入动画
 * @param animOut 退出动画
 */
fun Activity.startIntent(cls: Class<*>, bundle: Bundle, animIn: Int, animOut: Int) {
    startIntent(cls, bundle, animIn, animOut, -1, false)
}

fun Activity.startIntent(
        cls: Class<*>, bundle: Bundle, animIn: Int, animOut: Int, isFinish: Boolean
) {
    startIntent(cls, bundle, animIn, animOut, -1, isFinish)
}

/**
 * Activity 跳转(动画)
 *
 * @param cls     要跳转到的Activity
 * @param bundle  传递参数
 * @param animIn  进入动画
 * @param animOut 退出动画
 * @param delay   自定義延時（毫秒）
 */
fun Activity.startIntent(
        cls: Class<*>, bundle: Bundle?, animIn: Int, animOut: Int,
        delay: Int, isFinish: Boolean
) {
    //        if (manager.hasActivityCls(cls)) {
    //            return;
    //        }
    val intent = Intent()
    intent.setClass(this, cls)
    if (bundle != null)
        intent.putExtras(bundle)
    Handler().postDelayed({
        try {
            startActivity(intent)
            if (animIn != 0 && animOut != 0) {
                try {
                    overridePendingTransition(animIn, animOut)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
            if (isFinish)
                finish()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }, (if (delay < 0) DELAY else delay).toLong())
}

/**
 * Activity 跳转(需要返回结果)
 *
 * @param cls         要跳转到的Activity
 * @param requestCode 请求码
 */
fun Activity.startIntentForResult(cls: Class<*>, requestCode: Int) {
    startIntentForResult(cls, requestCode, 0, 0)
}

/**
 * Activity 跳转(需要返回结果)
 *
 * @param cls         要跳转到的Activity
 * @param requestCode 请求码
 * @param delay       自定義延時（毫秒）
 */
fun Activity.startIntentForResult(cls: Class<*>, requestCode: Int, delay: Int) {
    startIntentForResult(cls, requestCode, 0, 0, delay)
}

/**
 * Activity 跳转(需要返回结果,动画)
 *
 * @param cls         要跳转到的Activity
 * @param requestCode 请求码
 * @param animIn      进入动画
 * @param animOut     退出动画
 */
fun Activity.startIntentForResult(
        cls: Class<*>, requestCode: Int, animIn: Int,
        animOut: Int
) {
    startIntentForResult(cls, null, requestCode, animIn, animOut)
}

/**
 * Activity 跳转(需要返回结果,动画)
 *
 * @param cls         要跳转到的Activity
 * @param requestCode 请求码
 * @param animIn      进入动画
 * @param animOut     退出动画
 * @param delay       自定義延時（毫秒）
 */
fun Activity.startIntentForResult(
        cls: Class<*>, requestCode: Int, animIn: Int,
        animOut: Int, delay: Int
) {
    startIntentForResult(cls, null, requestCode, animIn, animOut, delay)
}

/**
 * Activity 跳转(需要返回结果,动画)
 *
 * @param cls         要跳转到的Activity
 * @param bundle      參數
 * @param requestCode 请求码
 * @param animIn      进入动画
 * @param animOut     退出动画
 */
fun Activity.startIntentForResult(
        cls: Class<*>, bundle: Bundle?, requestCode: Int,
        animIn: Int, animOut: Int
) {
    startIntentForResult(cls, bundle, requestCode, animIn, animOut, -1)
}

/**
 * Activity 跳转(需要返回结果,动画)
 *
 * @param cls         要跳转到的Activity
 * @param bundle      传递参数
 * @param requestCode 请求码
 * @param animIn      进入动画
 * @param animOut     退出动画
 * @param delay       自定義延時（毫秒）
 */
fun Activity.startIntentForResult(
        cls: Class<*>, bundle: Bundle?, requestCode: Int,
        animIn: Int, animOut: Int, delay: Int
) {
    //        if (manager.hasActivityCls(cls)) {
    //            return;
    //        }
    val intent = Intent()
    intent.setClass(this, cls)
    if (bundle != null)
        intent.putExtras(bundle)
    Handler().postDelayed({
        try {
            startActivityForResult(intent, requestCode)
            if (animIn != 0 && animOut != 0) {
                try {
                    overridePendingTransition(animIn, animOut)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }, (if (delay < 0) DELAY else delay).toLong())
}