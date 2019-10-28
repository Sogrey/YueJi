package top.sogrey.common.utils

import android.os.Environment
import android.os.Environment.getExternalStorageDirectory
import java.io.File


class SDCardUtils {
    companion object{

        /**
         * 获取SD路径
         */
        fun getSDPath(): String {
            val bool = Environment.getExternalStorageState() == "mounted"
            var localFile: File? = null
            if (bool)
                localFile = getExternalStorageDirectory()
            return localFile!!.toString()
        }
    }
}