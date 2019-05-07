package top.sogrey.yueji.utils

/**
 * 描述：
 * Created by Sogrey on 2018/12/17.
 */
class TimeUtils {
    companion object {
        fun getMMSS(seconds:Int):String{
            val MM = seconds/60
            val SS = seconds%60
            val MMStr = if(MM<10) "0$MM" else MM.toString()
            val SSStr = if(SS<10) "0$SS" else SS.toString()
            return "$MMStr:$SSStr"
        }
    }
}