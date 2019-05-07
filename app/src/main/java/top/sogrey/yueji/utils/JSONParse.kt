package top.sogrey.yueji.utils

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.text.TextUtils
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.json.JSONObject
import java.util.*

/**
 * 描述：JSON 解析与反解析
 * Created by Sogrey on 2019/1/18.
 */
class JSONParse {
    private var mGson: Gson? = null
    private val _mGetListBeansAsyns = LinkedList<GetListBeansAsyn<*>>()
    private val _mGetBeanAsyns = LinkedList<GetBeanAsyn<*>>()

    init {
        mGson = Gson()
    }

    companion object {
        val instance: JSONParse by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            JSONParse()
        }
    }

    /**
     * 对象转JSON字符串
     *
     * @param bean
     * @return
     */
    fun <T> Bean2Json(bean: T): String {
        return try {
            mGson!!.toJson(bean)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

    /**
     * 对象转JSON字符串，保留空值字段并序列化
     */
    fun <T> Bean2JsonAndSerializeNulls(bean: T): String {
        return try {
            GsonBuilder().serializeNulls().create().toJson(bean)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }

    }

    /**
     * JSON字符串转对象
     *
     * @param json
     * @param classOfT
     * @return
     */
    fun <T> Json2Bean(json: String, classOfT: Class<T>): T? {
        return try {
            mGson!!.fromJson(json, classOfT)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

    }

    /**
     * 生成字符串;
     *
     * @param text
     * @return
     */
    fun generateJson(text: String, key: String): String {
        return try {
            val obj = JSONObject(text)
            obj.getString(key).trim { it <= ' ' }
        } catch (e: Exception) {
            ""
        }
    }

    /**
     * 对象集合转JSON字符串
     *
     * @param beans
     * @param date
     * @param version
     * @return
     */
    fun <T> ListBean2Json(beans: List<T>, date: String, version: Double): String {
        return try {
            val builder = GsonBuilder()
            builder.excludeFieldsWithoutExposeAnnotation()// 不导出实体中没有@Expose
            builder.enableComplexMapKeySerialization()// 支持map key 更为复杂的格式
            builder.serializeNulls().setDateFormat(date)
            builder.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)// 字段首字母大写，@SerializedName注解的不会生效
            builder.setPrettyPrinting()// 格式化输出结果;
            builder.setVersion(version)
            mGson = builder.create()
            mGson!!.toJson(beans)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }

    }

    /**
     * JSON字符串转对象集合
     *
     * @param json
     * @param clazz
     * @return
     */
    fun <T> Json2ListBean(json: String, clazz: Class<Array<T>>): List<T>? {
        try {
            val arr = mGson!!.fromJson(json, clazz)
            return Arrays.asList(*arr)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    /**
     * 对象集合转Json
     * @param beans
     * @param <T>
     * @return
    </T> */
    fun <T> ListBean2Json(beans: List<T>): String? {
        return try {
            mGson!!.toJson(beans)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

    }

    /**
     * 异步解析JSON-解析集合<br></br>
     * 须先调用generateJson(text, key)找到集合
     *
     * @param tag
     * @param json      JSON字符串
     * @param classOfT  集合中item的model
     * @param _listener 监听器
     * @author Sogrey
     * @date 2015年6月26日
     */
    fun <T> parseListBeansInAsyncTask(tag: String, json: String, classOfT: Class<Array<T>>, _listener: JsonParseListListener<T>) {
        try {
            val asyn = GetListBeansAsyn(tag, json, classOfT, _listener)
            _mGetListBeansAsyns.add(asyn)
            asyn.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 异步解析JSON-解析实体对象<br></br>
     *
     * @param tag
     * @param json      JSON字符串
     * @param classOfT  集合中item的model
     * @param _listener 监听器
     * @author Sogrey
     * @date 2015年6月29日
     */
    fun <T> parseBeanInAsyncTask(tag: String, json: String, classOfT: Class<T>, _listener: JsonParseListener<T>) {
        try {
            val asyn = GetBeanAsyn(tag, json, classOfT, _listener)
            _mGetBeanAsyns.add(asyn)
            asyn.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * 解析集合异步
     *
     * @param <T>
     * @author Sogrey
     * @date 2015年6月29日
    </T> */
    @SuppressLint("StaticFieldLeak")
    private inner class GetListBeansAsyn<T> internal constructor(
            tag: String,
            json: String,
            private val _classOfT: Class<Array<T>>,// 解析的目标对象modle
            private val _listener: JsonParseListListener<T>?// 监听器
    ) : AsyncTask<Void, Void, List<T>>() {
        var _tag = ""// 任务tag标识，区分异步任务
        private var _json = ""// 请求回来的json字符串

        init {
            this._tag = tag
            this._json = json
        }

        override fun onPreExecute() {
            super.onPreExecute()
            println("开始解析....")
        }

        override fun doInBackground(vararg params: Void): List<T>? {
            return Json2ListBean(this._json, this._classOfT)
        }

        override fun onPostExecute(result: List<T>?) {
            super.onPostExecute(result)
            println("解析完成...")
            if (result != null) {// 成功解析
                if (this._listener != null) {
                    this._listener.onSuccess(_tag, result)
                }
            } else {// 解析失败
                if (this._listener != null) {
                    this._listener.onError(_tag, "解析出错")
                }
            }
            if (_listener != null) _listener.onFinish(_tag)// 异步请求结束
            this.cancel(true)
        }
    }

    /**
     * 解析实体对象异步
     *
     * @param <T>
     * @author Sogrey
     * @date 2015年6月29日
    </T> */

    @SuppressLint("StaticFieldLeak")
    private inner class GetBeanAsyn<T> internal constructor(
            tag: String, json: String, private val _classOfT: Class<T>,// 解析的目标对象modle
            private val _listener: JsonParseListener<T>?// 监听器
    ) : AsyncTask<Void, Void, T>() {
        var _tag = ""// 任务tag标识，区分异步任务
        private var _json = ""// 请求回来的json字符串

        init {
            this._tag = tag
            this._json = json
        }

        override fun onPreExecute() {
            super.onPreExecute()
            println("开始解析....")
        }

        override fun doInBackground(vararg params: Void): T? {
            return Json2Bean(this._json, this._classOfT)
        }

        override fun onPostExecute(result: T?) {
            super.onPostExecute(result)
            println("解析完成...")
            if (result != null) {// 成功解析
                if (this._listener != null) {
                    this._listener.onSuccess(_tag, result)
                }
            } else {// 解析失败
                if (this._listener != null) {
                    this._listener.onError(_tag, "解析出错")
                }
            }
            if (_listener != null) _listener.onFinish(_tag)// 异步请求结束
            this.cancel(true)
        }
    }

    /**
     * 取消指定tag异步解析任务
     *
     * @param tag 异步解析任务标识tag
     */

    fun cancleAsyncTask(tag: String) {
        if (_mGetListBeansAsyns.size > 0) {
            for (asyn in _mGetListBeansAsyns) {
                if (TextUtils.equals(tag, asyn._tag)) {
                    asyn.cancel(true)
                }
            }
        }
        if (_mGetBeanAsyns.size > 0) {
            for (asyn in _mGetBeanAsyns) {
                if (TextUtils.equals(tag, asyn._tag)) {
                    asyn.cancel(true)
                }
            }
        }
    }

    /**
     * 取消所有异步解析任务
     */
    fun cancleAllAsynctask() {
        if (_mGetListBeansAsyns.size > 0) {
            for (asyn in _mGetListBeansAsyns) {
                asyn.cancel(true)
            }
            _mGetListBeansAsyns.clear()
        }
        if (_mGetBeanAsyns.size > 0) {
            for (asyn in _mGetBeanAsyns) {
                asyn.cancel(true)
            }
            _mGetBeanAsyns.clear()
        }
    }

    /**
     * 解析实体对象
     */
    interface JsonParseListener<T> {
        fun onSuccess(_tag: String, _bean: T?)
        fun onError(_tag: String, errorMsg: String) {}
        fun onFinish(_tag: String) {}
    }

    /**
     * 解析实体集合
     */
    interface JsonParseListListener<T> {
        fun onSuccess(_tag: String, _listBean: List<T>?)
        fun onError(_tag: String, errorMsg: String) {}
        fun onFinish(_tag: String) {}
    }
}