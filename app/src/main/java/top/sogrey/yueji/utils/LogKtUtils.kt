package top.sogrey.yueji.utils

import android.util.Log

/**
 * 描述：
 * Created by Sogrey on 2018/12/5.
 */

fun logE(tag: String = Constants.APP_TAG, msg: String) {
    if (Constants.IS_DEBUG) Log.e(tag, msg)
}

fun logW(tag: String = Constants.APP_TAG, msg: String) {
    if (Constants.IS_DEBUG) Log.w(tag, msg)
}

fun logI(tag: String = Constants.APP_TAG, msg: String) {
    if (Constants.IS_DEBUG) Log.i(tag, msg)
}

fun logD(tag: String = Constants.APP_TAG, msg: String) {
    if (Constants.IS_DEBUG) Log.d(tag, msg)
}

fun logV(tag: String = Constants.APP_TAG, msg: String) {
    if (Constants.IS_DEBUG) Log.v(tag, msg)
}