package com.shetuans.zdshuhui.domain

/**
 * Created by Administrator on 2017/5/31.
 */
interface Source<T> {
    fun obtain(url: String): T
}