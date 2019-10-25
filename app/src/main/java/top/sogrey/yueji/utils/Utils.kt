package top.sogrey.yueji.utils

import android.util.Log
import java.io.*


/**
 * 描述：
 * Created by Sogrey on 2019/1/18.
 */


fun ReadTxtFile(strFilePath: String): String {
    val path = strFilePath
    val buffer = StringBuffer("")
    //打开文件
    val file = File(path)
    
    if (file.isFile){
        try {
            val instream = FileInputStream(file)
            var line: String?
            val buffreader = BufferedReader(InputStreamReader(FileInputStream(file), "utf-8"))
            //分行读取
            while (true) {
                line = buffreader.readLine()
                if(line==null) break
                buffer.append(line)
                buffer.append("\n")
            }
            instream.close()
        } catch (e: FileNotFoundException) {
            Log.d("TestFile", "The File doesn't not exist.")
        } catch (e: IOException) {
            Log.d("TestFile", e.message)
        }
    }
    return buffer.toString()
}

//fun ReadTxtFile(strFilePath: String,lisener:OnReadTxtCompleteListener){
//    async{
//       var txt =  ReadTxtFile(strFilePath)
//  runOnUiThread {
//      lisener.onComplete(txt)
//        }
//    }
//}
//
//interface OnReadTxtCompleteListener{
//    fun onComplete(txt:String)
//}