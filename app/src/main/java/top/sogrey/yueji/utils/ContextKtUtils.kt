package top.sogrey.yueji.utils

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.provider.Settings
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ProgressBar
import top.sogrey.yueji.utils.Constants.Companion.SP_NAME

/**
 * 描述：Context 扩展
 * Created by Sogrey on 2018/12/3.
 */

/**
 * 获取应用版本号Name
 */
fun Context.getAppVersion(): String {
    val packageManager = this.packageManager
    val packageInfo = packageManager.getPackageInfo(this.packageName, 0)
    logD("Context.getAppVersion", "versionName={${packageInfo.versionName}}")
    return packageInfo.versionName
}

/**
 * 获取应用版本号Code
 */
fun Context.getAppVersionCode(): Int {
    val packageManager = this.packageManager
    val packageInfo = packageManager.getPackageInfo(this.packageName, 0)
    logD("Context.getVersionCode", "versionCode={${packageInfo.versionCode}}")
    return packageInfo.versionCode
}

/**
 * 对话框
 */
fun Context.alert(title: String? = "", message: String? = "",
                  ok: String? = "", okListener: DialogInterface.OnClickListener? = null,
                  cancle: String? = "", cancleListener: DialogInterface.OnClickListener? = null,
                  ignore: String? = "", ignoreListener: DialogInterface.OnClickListener? = null,
                  customView: View? = null): AlertDialog? {
    var builder = AlertDialog.Builder(this)
    builder.setTitle(title!!)
            .setMessage(message!!)
            .setPositiveButton(ok!!, okListener)
            .setNegativeButton(cancle!!, cancleListener)
            .setNeutralButton(ignore!!, ignoreListener)
            // 禁止响应按back键的事件
            .setCancelable(false)
    if (customView != null) builder.setView(customView)
    var dialog = builder.create()
    dialog.show()
    return dialog
}

@SuppressLint("RtlHardcoded")
fun Context.waitingDialog(title: String? = "", message: String? = "",
                          ok: String? = "", okListener: DialogInterface.OnClickListener? = null,
                          cancle: String? = "", cancleListener: DialogInterface.OnClickListener? = null,
                          ignore: String? = "", ignoreListener: DialogInterface.OnClickListener? = null): AlertDialog? {
    var builder = AlertDialog.Builder(this)
    val customView = ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal)
    customView.isIndeterminate = true
//    customView.progressDrawable = ClipDrawable(ColorDrawable(Color.parseColor("#3F51B5")), Gravity.LEFT, ClipDrawable.HORIZONTAL)
    builder.setTitle(title!!)
            .setMessage(message!!)
            .setView(customView)
            .setPositiveButton(ok!!, okListener)
            .setNegativeButton(cancle!!, cancleListener)
            .setNeutralButton(ignore!!, ignoreListener)
            // 禁止响应按back键的事件
            .setCancelable(false)
    var dialog = builder.create()
    dialog.show()
    return dialog
}

/**
 * 跳转设置页面
 *
 * @param context 上下文
 * @param action
 * [android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS] 跳转系统的辅助功能界面
 * [android.provider.Settings.ACTION_ADD_ACCOUNT] 显示添加帐户创建一个新的帐户屏幕。【测试跳转到微信登录界面】
 * [android.provider.Settings.ACTION_AIRPLANE_MODE_SETTINGS] 飞行模式
 * [android.provider.Settings.ACTION_WIRELESS_SETTINGS] 无线网和网络设置界面
 * [android.provider.Settings.ACTION_APN_SETTINGS] 跳转 APN设置界面
 * [android.provider.Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS] 跳转开发人员选项界面
 * [android.provider.Settings.ACTION_APPLICATION_SETTINGS] 跳转应用程序列表界面
 * [android.provider.Settings.ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS] 跳转到应用程序界面【所有的】
 * [android.provider.Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS] 跳转 应用程序列表界面【已安装的】
 * [android.provider.Settings.ACTION_BLUETOOTH_SETTINGS] 跳转系统的蓝牙设置界面
 * [android.provider.Settings.ACTION_DATA_ROAMING_SETTINGS] 跳转到移动网络设置界面
 * [android.provider.Settings.ACTION_DATE_SETTINGS] 跳转日期时间设置界面
 * [android.provider.Settings.ACTION_DEVICE_INFO_SETTINGS] 跳转手机状态界面
 * [android.provider.Settings.ACTION_DISPLAY_SETTINGS] 跳转手机显示界面
 * [android.provider.Settings.ACTION_DREAM_SETTINGS] 【API 18及以上 没测试，不一定存在】
 * [android.provider.Settings.ACTION_INPUT_METHOD_SETTINGS] 跳转语言和输入设备
 * [android.provider.Settings.ACTION_INPUT_METHOD_SUBTYPE_SETTINGS] 【API 11及以上】  //  跳转 语言选择界面 【多国语言选择】
 * [android.provider.Settings.ACTION_INTERNAL_STORAGE_SETTINGS]  跳转存储设置界面【内部存储】
 * [android.provider.Settings.ACTION_MEMORY_CARD_SETTINGS] 跳转 存储设置 【记忆卡存储】
 * [android.provider.Settings.ACTION_LOCALE_SETTINGS] 跳转语言选择界面【仅有English 和 中文两种选择】
 * [android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS] 跳转位置服务界面【管理已安装的应用程序。】
 * [android.provider.Settings.ACTION_NETWORK_OPERATOR_SETTINGS] 跳转到 显示设置选择网络运营商。
 * [android.provider.Settings.ACTION_NFCSHARING_SETTINGS] 显示NFC共享设置。 【API 14及以上】
 * [android.provider.Settings.ACTION_NFC_SETTINGS] 显示NFC设置。这显示了用户界面,允许NFC打开或关闭。【API 16及以上】
 * [android.provider.Settings.ACTION_PRIVACY_SETTINGS] 跳转到备份和重置界面
 * [android.provider.Settings.ACTION_QUICK_LAUNCH_SETTINGS] 跳转快速启动设置界面
 * [android.provider.Settings.ACTION_SEARCH_SETTINGS] 跳转到 搜索设置界面
 * [android.provider.Settings.ACTION_SECURITY_SETTINGS] 跳转到安全设置界面
 * [android.provider.Settings.ACTION_SETTINGS] 跳转到设置界面
 * [android.provider.Settings.ACTION_SOUND_SETTINGS] 跳转到声音设置界面
 * [android.provider.Settings.ACTION_SYNC_SETTINGS]跳转账户同步界面
 * [android.provider.Settings.ACTION_USER_DICTIONARY_SETTINGS]跳转用户字典界面
 * [android.provider.Settings.ACTION_WIFI_IP_SETTINGS]跳转到IP设定界面
 * [android.provider.Settings.ACTION_WIFI_SETTINGS]跳转Wifi列表设置
 *
 */
fun Context.go2Settings(action: String) {
    val intent = Intent(action)
    try {
        startActivity(intent)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

/**
 * 根据包名跳转到系统自带的应用程序信息界面
 *
 * @param context 上下文
 */
fun Context.goApplicationSetting() {
    val packageURI = Uri.parse("package:$packageName")
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI)
    startActivity(intent)
}

/**
 * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
 * @param context
 * @param key
 * @param object
 */
fun Context.setSP(key: String, `object`: Any?) {
    val sp = getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
    val editor = sp.edit()

    if (`object` == null) {
        editor.putString(key, "null")
        return
    } else {
        val type = `object`.javaClass.simpleName
        when (type) {
            "String" -> editor.putString(key, `object` as String?)
            "Integer" -> editor.putInt(key, (`object` as Int?)!!)
            "Boolean" -> editor.putBoolean(key, (`object` as Boolean?)!!)
            "Float" -> editor.putFloat(key, (`object` as Float?)!!)
            "Long" -> editor.putLong(key, (`object` as Long?)!!)
        }
    }
    editor.apply()
}


/**
 * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
 * @param context
 * @param key
 * @param defaultObject
 * @return
 */
fun Context.getSP(key: String, defaultObject: Any): Any? {
    val type = defaultObject.javaClass.simpleName
    val sp = getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)

    return when (type) {
        "String" -> sp.getString(key, defaultObject as String)
        "Integer" -> sp.getInt(key, defaultObject as Int)
        "Boolean" -> sp.getBoolean(key, defaultObject as Boolean)
        "Float" -> sp.getFloat(key, defaultObject as Float)
        "Long" -> sp.getLong(key, defaultObject as Long)
        else -> {
        }
    }
}

fun Context.getResString(stringId: Int): String = resources.getString(stringId)

fun Context.getResString(resId: Int, vararg o: Any): String = resources.getString(resId, *o)

fun Context.getResDrawable(@DrawableRes id: Int): Drawable? = ContextCompat.getDrawable(this, id)

fun Context.getResColor(@ColorRes id: Int): Int = ContextCompat.getColor(this, id)


