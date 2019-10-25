package top.sogrey.yueji.db

/**
 * 描述：
 * Created by Sogrey on 2018/12/6.
 */
class DBHelper {
    companion object {
        private var mDBHelper: DBHelper? = null
        fun getInstance(): DBHelper? {
            if(mDBHelper ==null) mDBHelper = DBHelper()
            return mDBHelper
        }
    }

//    var mDButils:DBUtils?=null
//    private fun DBHelper(){
//        mDButils = DBUtils.INSTANCE
//    }
//
//    fun init(context: Context){
//        if(mDButils==null){
//            throw NullPointerException("DBUtils is null,please call DBHelper.getInstance() frist.")
//        }
//        mDButils!!.init(context)
//    }
}