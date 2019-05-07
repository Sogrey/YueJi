package top.sogrey.yueji.utils

import android.content.Context
import android.net.ConnectivityManager
import android.telephony.TelephonyManager
import java.net.NetworkInterface
import java.net.SocketException
import java.util.*
import java.util.regex.Pattern

/**
 * 网络相关工具类
 * Created by Sogrey on 2017/9/23.
 */
class NetWorkUtils {
    /**
     * 判断是否有网络连接
     * @param context
     * @return
     */
    fun isNetworkConnected(context: Context?): Boolean {
        if (context != null) {
            val mConnectivityManager = context!!
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val mNetworkInfo = mConnectivityManager!!.activeNetworkInfo
            if (mNetworkInfo != null) {
                return mNetworkInfo!!.isAvailable
            }
        }
        return false
    }


    /**
     * 判断WIFI网络是否可用
     * @param context
     * @return
     */
    fun isWifiConnected(context: Context?): Boolean {
        if (context != null) {
            val mConnectivityManager = context!!
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val mWiFiNetworkInfo = mConnectivityManager!!
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo!!.isAvailable
            }
        }
        return false
    }


    /**
     * 判断MOBILE网络是否可用
     * @param context
     * @return
     */
    fun isMobileConnected(context: Context?): Boolean {
        if (context != null) {
            val mConnectivityManager = context!!
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val mMobileNetworkInfo = mConnectivityManager!!
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo!!.isAvailable
            }
        }
        return false
    }


    /**
     * 获取当前网络连接的类型信息
     * @param context
     * @return
     */
    fun getConnectedType(context: Context?): Int {
        if (context != null) {
            val mConnectivityManager = context!!
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val mNetworkInfo = mConnectivityManager!!.activeNetworkInfo
            if (mNetworkInfo != null && mNetworkInfo!!.isAvailable) {
                return mNetworkInfo!!.type
            }
        }
        return -1
    }


    /**
     * 获取当前的网络状态 ：没有网络0：WIFI网络1：3G网络2：2G网络3
     *
     * @param context
     * @return
     */
    fun getAPNType(context: Context): Int {
        var netType = 0
        val connMgr = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr!!.activeNetworkInfo ?: return netType
        val nType = networkInfo!!.type
        if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = 1// wifi
        } else if (nType == ConnectivityManager.TYPE_MOBILE) {
            val nSubType = networkInfo!!.subtype
            val mTelephony = context
                .getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            if (nSubType == TelephonyManager.NETWORK_TYPE_UMTS && !mTelephony!!.isNetworkRoaming) {
                netType = 2// 3G
            } else {
                netType = 3// 2G
            }
        }
        return netType
    }

    /**
     * 判断当前网络类型
     * @return
     */
    fun GetNetworkType(context: Context): Int {
        var strNetworkType = 0

        if (!isNetworkConnected(context)) {
            return strNetworkType
        }
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr!!.activeNetworkInfo
        if (networkInfo != null && networkInfo!!.isConnected) {
            if (networkInfo!!.type == ConnectivityManager.TYPE_WIFI) {
                strNetworkType = 1//wifi
            } else if (networkInfo!!.type == ConnectivityManager.TYPE_MOBILE) {
                val _strSubTypeName = networkInfo!!.subtypeName

                logE("cocos2d-x", "Network getSubtypeName : $_strSubTypeName")

                // TD-SCDMA   networkType is 17
                val networkType = networkInfo!!.subtype
                when (networkType) {
                    TelephonyManager.NETWORK_TYPE_GPRS, TelephonyManager.NETWORK_TYPE_EDGE, TelephonyManager.NETWORK_TYPE_CDMA, TelephonyManager.NETWORK_TYPE_1xRTT, TelephonyManager.NETWORK_TYPE_IDEN //api<8 : replace by 11
                    -> strNetworkType = 2//2G
                    TelephonyManager.NETWORK_TYPE_UMTS, TelephonyManager.NETWORK_TYPE_EVDO_0, TelephonyManager.NETWORK_TYPE_EVDO_A, TelephonyManager.NETWORK_TYPE_HSDPA, TelephonyManager.NETWORK_TYPE_HSUPA, TelephonyManager.NETWORK_TYPE_HSPA, TelephonyManager.NETWORK_TYPE_EVDO_B //api<9 : replace by 14
                        , TelephonyManager.NETWORK_TYPE_EHRPD  //api<11 : replace by 12
                        , TelephonyManager.NETWORK_TYPE_HSPAP  //api<13 : replace by 15
                    -> strNetworkType = 3//3G
                    TelephonyManager.NETWORK_TYPE_LTE    //api<11 : replace by 13
                    -> strNetworkType = 4//4G
                    else ->
                        // http://baike.baidu.com/item/TD-SCDMA 中国移动 联通 电信 三种3G制式
                        if (_strSubTypeName.equals("TD-SCDMA", ignoreCase = true) || _strSubTypeName.equals(
                                "WCDMA",
                                ignoreCase = true
                            ) || _strSubTypeName.equals("CDMA2000", ignoreCase = true)
                        ) {
                            strNetworkType = 3//3G
                        } else {
                            strNetworkType = 1//默认WiFi
                        }
                }

                logE("cocos2d-x", "Network getSubtype : " + Integer.valueOf(networkType).toString())
            }
        }

        logE("cocos2d-x", "Network Type : $strNetworkType")

        return strNetworkType
    }


    /**
     * Tet local ip address.
     *
     * @return Such as: `192.168.1.1`.
     */
    fun getLocalIPAddress(): String {
        var enumeration: Enumeration<NetworkInterface>? = null
        try {
            enumeration = NetworkInterface.getNetworkInterfaces()
        } catch (e: SocketException) {
        }

        if (enumeration != null) {
            // 遍历所用的网络接口
            while (enumeration!!.hasMoreElements()) {
                // 得到每一个网络接口绑定的地址
                val nif = enumeration!!.nextElement()
                val inetAddresses = nif.inetAddresses
                // 遍历每一个接口绑定的所有ip
                if (inetAddresses != null) {
                    while (inetAddresses!!.hasMoreElements()) {
                        val ip = inetAddresses!!.nextElement()
                        if (!ip.isLoopbackAddress && isIPv4Address(ip.hostAddress)) {
                            return ip.hostAddress
                        }
                    }
                }
            }
        }

        return "locahost"
    }

    /**
     * Ipv4 address check.
     */
    private val IPV4_PATTERN = Pattern.compile(
        "^(" +
                "([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}" +
                "([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$"
    )

    /**
     * Check if valid IPV4 address.
     *
     * @param input the address string to check for validity.
     * @return True if the input parameter is a valid IPv4 address.
     */
    fun isIPv4Address(input: String): Boolean {
        return IPV4_PATTERN.matcher(input).matches()
    }
}