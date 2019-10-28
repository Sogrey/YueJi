package top.sogrey.yueji.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_share_receive.*
import org.jetbrains.anko.async
import org.jetbrains.anko.longToast
import org.jsoup.Jsoup
import top.sogrey.common.utils.RegUtils
import top.sogrey.common.utils.loadImage
import top.sogrey.yueji.R
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class ShareReceiveActivity: AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_receive)


        val intent = intent
        val action = intent.action
        val type = intent.type

        if (Intent.ACTION_SEND == action && type != null) {
            val uri = intent.getParcelableExtra<Uri>(Intent.EXTRA_STREAM)
            when {
                type.startsWith("text/") -> {// 处理发送来文本
                    dealTextMessage(intent)
                }
                type.startsWith("image/") -> {// 处理发送来图片
//                    longToast("处理发送来图片$uri")
//                    dealPicStream(intent)
//                    result.append("处理发送来图片$uri\n\n")
                }
                type.startsWith("audio/") -> {// 处理发送来音频
                    longToast("处理发送来音频$uri")
//                    result.append("处理发送来音频$uri\n\n")
                }
                type.startsWith("video/") -> {// 处理发送来的视频
                    longToast("处理发送来的视频$uri")
//                    result.append("处理发送来的视频$uri\n\n")
                }
                else -> {//处理发送过来的其他文件
                    longToast("处理发送过来的其他文件$uri")
//                    result.append("处理发送过来的其他文件$uri\n\n")
                }
            }
        } else if (Intent.ACTION_SEND_MULTIPLE == action && type != null) {
            val arrayList = intent.getParcelableArrayListExtra<Parcelable>(Intent.EXTRA_STREAM)
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
        val title: String? = intent.getStringExtra(Intent.EXTRA_TITLE)
        val subject: String? = intent.getStringExtra(Intent.EXTRA_SUBJECT)

        val newTitle = title ?: subject
        txt_title.setText(newTitle ?: "")
        edt_note.setText(share)

        val urls = RegUtils.find(share, RegUtils.REG_HTTP)
        val url = urls[0]
        txt_url.text = Html.fromHtml("<a href=\"$url\">$url</a>")

        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")//设置日期格式
        txt_acquisition_time.text = "采集时间：${df.format(Date())}"

        getWebTitle(url)
//        wv_WebView.loadUrl(url)


    }

    private fun getWebTitle(url: String) {
        async {
            try {
                var baseUrlProtocol = url.split("//")[0]
                val doc = Jsoup.connect(url).get()
                val links = doc.select("head")
                val titleLinks = links[0].select("title").first()
                val title = titleLinks.text()
                val authorLinks =doc.body().select(".author>.info>.name>a").first()
                val author = authorLinks.text()
                val iconLinkEle = doc.head().select("link[href~=.*\\.(ico|png)]").first()
                var iconLink = iconLinkEle.attr("href")
                if (iconLink.startsWith("//")) {
                    iconLink = "$baseUrlProtocol$iconLink"
                }
                val publishCssQuery = getPublishTimeCssQuery(url)
                var publishTime = "..."
                if (null != publishCssQuery) {
                    val publishTimeEle = doc.body().select(publishCssQuery).first()
                    publishTime = publishTimeEle.text()
                }
                publishTime = processing(publishTime)
                val firstImageEle = doc.body().select(".show-content").select("img").first()
                //.select("img[src~=.*\\.(jpg|jpeg|png|webp)]").first()
                var firstImage = firstImageEle!!.attr("data-original-src")
                if (firstImage.startsWith("//")) {
                    firstImage = "$baseUrlProtocol$firstImage"
                }
                runOnUiThread {
                    txt_title.setText(title)
                    txt_author.text = "作者：$author"
                    txt_published_time.text = "发布时间：$publishTime"
                    img_source_icon.loadImage(iconLink)
                    img_firstIamge.loadImage(firstImage)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * 时间处理，主要针对 今天、昨天、一周前之类的时间
     */
    private fun processing(publishTime: String): String {
        //今天、昨天、前天、一周前、一月前、一年前
        return publishTime.replace("编辑于", "").replace("发布于", "").trim()
    }

    /**
     * 获取发布时间的css选择器
     */
    private fun getPublishTimeCssQuery(url: String): String? {
        return when {
            url.startsWith("https://www.jianshu.com/") -> {//简书
                ".publish-time"
            }
            url.startsWith("https://juejin.im/") -> {//掘金
                "time.time"
            }
            url.startsWith("https://zhuanlan.zhihu.com/") -> {//知乎专栏
                ".ContentItem-time"
            }
            url.startsWith("https://www.zhihu.com/question/") -> {//知乎问答
                ".ContentItem-time>a>span"
            }
            url.startsWith("https://mp.weixin.qq.com/") -> {//微信
                "#publish_time"
            }
            else -> null
        }
    }
}