package com.shetuans.zdshuhui.domain

/**
 * Created by Administrator on 2017/5/31.
 */

data class Cover(val coverUrl: String, val title: String, val link: String)
data class Comic(val url: String)


data class News(val title: String, val createdTime: String, val link: String)

data class NewsContainer(val title: String, val newsList: List<News>)

data class BookInfo(val updateTime: String, val description: String)

data class Page(val title: String, val link: String)

data class BookDetail(val pages: List<Page>, val info: BookInfo) {
    operator fun get(position: Int) = pages[position]
    fun size() = pages.size
}