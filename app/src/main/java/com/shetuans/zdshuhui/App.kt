package com.shetuans.zdshuhui

import android.app.Application
import com.squareup.picasso.LruCache
import com.squareup.picasso.Picasso

/**
 * Created by Administrator on 2017/5/28.
 */
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        val maxMemory = Runtime.getRuntime().maxMemory().toInt()
        Picasso.Builder(this).memoryCache(LruCache(maxMemory / 8)).build()
    }
}