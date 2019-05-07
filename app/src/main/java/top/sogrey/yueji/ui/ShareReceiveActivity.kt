package top.sogrey.yueji.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.os.Parcelable
import kotlinx.android.synthetic.main.activity_share_receive.*
import org.jetbrains.anko.longToast
import android.content.Intent.EXTRA_STREAM
import org.jetbrains.anko.async
import org.jsoup.Jsoup
import top.sogrey.yueji.R
import top.sogrey.yueji.utils.RegUtils
import top.sogrey.yueji.utils.logI


class ShareReceiveActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_receive)


        val intent = intent
        val action = intent.action
        val type = intent.type
        var result = StringBuffer()
        result.append("处理发送来分享$intent\n\n")
        if (Intent.ACTION_SEND == action && type != null) {
            val uri = intent.getParcelableExtra<Uri>(EXTRA_STREAM)
            when {
                type.startsWith("text/") -> {// 处理发送来文本
//                    longToast("处理发送来图片$uri")
                    dealTextMessage(intent)

                    val share = intent.getStringExtra(Intent.EXTRA_TEXT)
                    val title = intent.getStringExtra(Intent.EXTRA_TITLE)
                    val subject = intent.getStringExtra(Intent.EXTRA_SUBJECT)
                    result.append(
                        "处理发送来文本:title--$title\n\nsubject--$subject\n" +
                                "\ntext--$share"
                    )
                }
                type.startsWith("image/") -> {// 处理发送来图片
//                    longToast("处理发送来图片$uri")
//                    dealPicStream(intent)
                    result.append("处理发送来图片$uri\n\n")
                }
                type.startsWith("audio/") -> {// 处理发送来音频
                    longToast("处理发送来音频$uri")
                    result.append("处理发送来音频$uri\n\n")
                }
                type.startsWith("video/") -> {// 处理发送来的视频
                    longToast("处理发送来的视频$uri")
                    result.append("处理发送来的视频$uri\n\n")
                }
                else -> {//处理发送过来的其他文件
                    longToast("处理发送过来的其他文件$uri")
                    result.append("处理发送过来的其他文件$uri\n\n")
                }
            }
        } else if (Intent.ACTION_SEND_MULTIPLE == action && type != null) {
            val arrayList = intent.getParcelableArrayListExtra<Parcelable>(EXTRA_STREAM)
            when {
                type.startsWith("image/") -> {// 处理发送来多张图片
//                    dealMultiplePicStream(intent)
                }
                type.startsWith("audio/") -> {
                    // 处理发送来的多个音频
                    longToast("处理发送过来的其他文件${arrayList.size}")
                }
                type.startsWith("video/") -> {
                    //处理发送过来的多个视频
                    longToast("处理发送过来的其他文件${arrayList.size}")
                }
                else -> {
                    //处理发送过来的多个文件
                    longToast("处理发送过来的其他文件${arrayList.size}")
                }
            }
        }

        shareResult.text = result.toString()
        logI(msg = result.toString())
    }

//    private fun dealMultiplePicStream(intent: Intent) {
//        val arrayList = intent.getParcelableArrayListExtra(EXTRA_STREAM)
//    }
//
//    private fun dealPicStream(intent: Intent) {
//        val uri = intent.getParcelableExtra(EXTRA_STREAM)
//    }

    private fun dealTextMessage(intent: Intent) {
        val share = intent.getStringExtra(Intent.EXTRA_TEXT)
//        val title = intent.getStringExtra(Intent.EXTRA_TITLE)

        var urls = RegUtils.find(share, RegUtils.REG_HTTP)
        logI(msg = "1==$urls")
        var url = urls[0]
        logI(msg = "2==$url")
        getWebTitle(url)
    }

    fun getWebTitle(url: String) {
        async {
            try {
                val doc = Jsoup.connect(url).get()
                val links = doc.select("head")
                val titleLinks = links[0].select("title")
                var title = titleLinks[0].text()
                return@async runOnUiThread {
                    logI(msg = "3==$title")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
