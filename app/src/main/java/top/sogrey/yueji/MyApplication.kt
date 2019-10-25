package top.sogrey.yueji

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
//import com.raizlabs.android.dbflow.config.DatabaseConfig
//import com.raizlabs.android.dbflow.config.FlowConfig
//import com.raizlabs.android.dbflow.config.FlowLog
//import com.raizlabs.android.dbflow.config.FlowManager.*
import top.sogrey.yueji.db.AppDatabase

class MyApplication  : Application() {

    var wpx: Int = 0
    var hpx: Int = 0
    var dataFrist: Long = 0

    companion object {
        private var mThis: MyApplication? = null
        fun getInstance(): MyApplication? {
            return mThis
        }
    }

    override fun onCreate() {
        super.onCreate()
        mThis = this

        val mDisplayMetrics = DisplayMetrics()
        val mWm = getWindowManager()
        mWm.defaultDisplay.getMetrics(mDisplayMetrics)
        wpx = mDisplayMetrics.widthPixels
        hpx = mDisplayMetrics.heightPixels

        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.memoryClass
        activityManager.largeMemoryClass

        initDBFlow()

    }

    /**
     * FlowManager 初始化
     */
    private fun initDBFlow() {

//        init(
//            FlowConfig.builder(this)
//                .openDatabasesOnInit(true)
//                .addDatabaseConfig(
//                    DatabaseConfig.builder(AppDatabase.javaClass)
////                                        .openHelper(object : CustomFlowSQliteOpenHelper() {}) //添加自定义FlowSQLiteOpenHelper
//                        .databaseName(AppDatabase.NAME) //修改数据库名 从4.1.0开始最好在初始化时传递它FlowManager
//                        .build()
//                ).build()
//        )
//        FlowLog.setMinimumLoggingLevel(FlowLog.Level.V) // set to verbose logging

//        要动态更改数据库名称
//        FlowManager.getDatabase(AppDatabase.javaClass)
//                .reset(DatabaseConfig.builder(AppDatabase.javaClass)
//                        .databaseName("AppDatabase-2")
//                        .build())
//        database<AppDatabase>()
//                .reset(DatabaseConfig.builder(AppDatabase.javaClass)
//                        .databaseName(AppDatabase.NAME)
//                        .build())
    }
    private fun getWindowManager(): WindowManager {
        return getSystemService(Context.WINDOW_SERVICE) as WindowManager
    }
}