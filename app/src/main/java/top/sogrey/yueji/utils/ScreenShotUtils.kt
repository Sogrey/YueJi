package top.sogrey.yueji.utils

import android.app.Activity
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.RecyclerView
import android.util.LruCache
import android.view.View
import android.widget.ListView
import android.widget.ScrollView
import java.util.ArrayList

/**
 * 描述：View 截图
 * Created by Sogrey on 2018/12/12.
 */
class ScreenShotUtils {
    /**
     * 获取指定Activity的截屏
     */
    fun activityScreenShot(activity: Activity): Bitmap {
        // View是你需要截图的View
        val view = activity.window.decorView
        view.isDrawingCacheEnabled = true
        view.buildDrawingCache()
        val bitmap = view.drawingCache

        // 获取状态栏高度
        val frame = Rect()
        activity.window.decorView.getWindowVisibleDisplayFrame(frame)
        val statusBarHeight = frame.top

        val outSize = Point()
        activity.windowManager.defaultDisplay.getSize(outSize)

        // 获取屏幕长和高
        val width = outSize.x //activity.getWindowManager().getDefaultDisplay().getWidth();
        val height = outSize.y// activity.getWindowManager().getDefaultDisplay().getHeight();

        // 去掉标题栏
        val b = Bitmap.createBitmap(bitmap, 0, statusBarHeight, width, height - statusBarHeight)
        view.destroyDrawingCache()

        return b
    }

    /**
     * 获取scrollview的截屏
     *
     *
     * 因为ScrollView只有一个childView，虽然没有全部显示在界面上，但是已经全部渲染绘制，因此可以直接调用scrollView.draw(canvas)来完成截图
     */
    fun scrollViewScreenShot(scrollView: ScrollView): Bitmap {
        var h = 0
        var bitmap: Bitmap? = null
        for (i in 0 until scrollView.childCount) {
            h += scrollView.getChildAt(i).height
            scrollView.getChildAt(i).setBackgroundColor(Color.parseColor("#ffffff"))
        }
        bitmap = Bitmap.createBitmap(scrollView.width, h, Bitmap.Config.RGB_565)
        val canvas = Canvas(bitmap!!)
        scrollView.draw(canvas)
        return bitmap
    }

    /**
     * 获取listview的截屏
     *
     *
     * 因为listview布局复用的问题并且只能绘制在屏幕上显示的ItemView，所以我们需要把它的item放到一个view的集合里最后通过拼接来完成整图的效果。但是当Item足够多的时候，肯定会发生oom的所以记得在获得bitmap之后对其进行压缩处理。
     *
     * @param listview
     * @return
     */
    fun shotListView(listview: ListView): Bitmap {
        val adapter = listview.adapter
        val itemscount = adapter.count
        var allitemsheight = 0
        val bmps = ArrayList<Bitmap>()

        //循环对listview的item进行截图， 最后拼接在一起
        for (i in 0 until itemscount) {
            val childView = adapter.getView(i, null, listview)
            childView.measure(
                View.MeasureSpec.makeMeasureSpec(listview.width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            )
            childView.layout(0, 0, childView.measuredWidth, childView.measuredHeight)
            childView.isDrawingCacheEnabled = true
            childView.buildDrawingCache()

            bmps.add(childView.drawingCache)
            allitemsheight += childView.measuredHeight
            //这里可以把listview中单独的item进行保存
            //            viewSaveToImage(childView.getDrawingCache());
        }
        val w = listview.measuredWidth
        val bigbitmap = Bitmap.createBitmap(w, allitemsheight, Bitmap.Config.ARGB_8888)
        val bigcanvas = Canvas(bigbitmap)

        val paint = Paint()
        var iHeight = 0

        for (i in bmps.indices) {
            var bmp: Bitmap? = bmps[i]
            bigcanvas.drawBitmap(bmp!!, 0f, iHeight.toFloat(), paint)
            iHeight += bmp.height

            bmp.recycle()
            bmp = null
        }
        return bigbitmap
    }

    /**
     * recycleview截图
     *
     *
     * recycleview和listView使用相同的方案
     *
     * @param view
     * @return
     */
    fun shotRecyclerView(view: RecyclerView): Bitmap? {
        val adapter = view.adapter
        var bigBitmap: Bitmap? = null
        if (adapter != null) {
            val size = adapter.itemCount
            var height = 0
            val paint = Paint()
            var iHeight = 0
            val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()

            // Use 1/8th of the available memory for this memory cache.
            val cacheSize = maxMemory / 8
            val bitmaCache = LruCache<String, Bitmap>(cacheSize)
            for (i in 0 until size) {
                val holder = adapter.createViewHolder(view, adapter.getItemViewType(i))
                adapter.onBindViewHolder(holder, i)
                holder.itemView.measure(
                    View.MeasureSpec.makeMeasureSpec(view.width, View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                )
                holder.itemView.layout(
                    0, 0, holder.itemView.measuredWidth,
                    holder.itemView.measuredHeight
                )
                holder.itemView.isDrawingCacheEnabled = true
                holder.itemView.buildDrawingCache()
                val drawingCache = holder.itemView.drawingCache
                if (drawingCache != null) {
                    bitmaCache.put(i.toString(), drawingCache)
                }
                height += holder.itemView.measuredHeight
            }

            bigBitmap = Bitmap.createBitmap(view.measuredWidth, height, Bitmap.Config.ARGB_8888)
            val bigCanvas = Canvas(bigBitmap!!)
            val lBackground = view.background
            if (lBackground is ColorDrawable) {
                val lColor = lBackground.color
                bigCanvas.drawColor(lColor)
            }

            for (i in 0 until size) {
                val bitmap = bitmaCache.get(i.toString())
                bigCanvas.drawBitmap(bitmap, 0f, iHeight.toFloat(), paint)
                iHeight += bitmap.height
                bitmap.recycle()
            }
        }
        return bigBitmap
    }

    /**
     * 任意View转bitmap
     */
    fun viewConversionBitmap(v: View): Bitmap {
        return viewConversionBitmap(v, 1)
    }

    /**
     * @param v
     * @param zoom 缩放倍数 1不缩放 >1缩小  <1 放大
     * @return
     */
    fun viewConversionBitmap(v: View, zoom: Int): Bitmap {
        if (zoom <= 0) {
            throw IllegalArgumentException("缩放倍数不得小于等于0")
        }
        val w = v.width / zoom
        val h = v.height / zoom

        val bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        val c = Canvas(bmp)

        c.drawColor(Color.WHITE)
        /** 如果不设置canvas画布为白色，则生成透明  */

        v.layout(0, 0, w, h)
        v.draw(c)

        return bmp
    }
}