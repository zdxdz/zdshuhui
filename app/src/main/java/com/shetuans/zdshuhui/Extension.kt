package com.shetuans.zdshuhui

import android.content.Context
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.widget.Toast
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Created by Administrator on 2017/5/28.
 * 拓展方法
 */

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun View.snackbar(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, message, duration).show()
}

/**
 * inline: 内联
 * reified: 使具体化
 * 此函数在编译时候直接替换，并且泛型T具体化到对应类型
 */
inline fun <reified T> T.log(message: Any) {
    Log.i(T::class.java.simpleName, message.toString())
}

fun getHtml(url: String) : String? {
    val client = OkClient.shareInstance()
    val request = Request.Builder()
            .url(url)
            .build()

    val response = client.newCall(request).execute()
    return response?.body()?.string()
}

fun WebView.load(html: String) {
    this.loadDataWithBaseURL("http://ishuhui.net/", html, "text/html", "charset=utf-8", null)
}

/**
 * 单例OkHttpClient
 */
object OkClient {
    val client = OkHttpClient()
    fun shareInstance() = client
}

