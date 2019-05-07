package top.sogrey.yueji.utils

import top.sogrey.yueji.BuildConfig

/**
 * 描述：
 * Created by Sogrey on 2018/12/3.
 */
class Constants {
    companion object {
        val IS_DEBUG = BuildConfig.DEBUG//Debug

        //SP
        const val APP_TAG = "YueJi"
        const val SP_NAME = "YueJi"
        const val SP_APP_VERSION = "AppVersion"//上一次检测到的app版本
    }
}
