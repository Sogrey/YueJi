package top.sogrey.common

import android.app.Activity
import android.app.Application
import top.sogrey.common.utils.AppUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Bundle


open class BaseApplication : Application() {

    //当前Activity，当app退出到后台后这个curActivity会变为null
    private var curActivity: Activity? = null
    override fun onCreate() {
        super.onCreate()
        AppUtils.init(this)

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityResumed(activity: Activity) {
                curActivity = activity
            }

            override fun onActivityPaused(activity: Activity) {
                curActivity = null
            }

            override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivityDestroyed(activity: Activity) {
            }
        })
    }

    fun isDebug(debug: Boolean) {
        AppConfig.DEBUG = BuildConfig.DEBUG && debug
    }
}