package com.shetuans.zdshuhui.domain

import com.shetuans.zdshuhui.getHtml
import org.jsoup.Jsoup

/**
 * Created by Administrator on 2017/5/31.
 */
class BookSource: Source<ArrayList<Cover>> {
    override fun obtain(url: String): ArrayList<Cover> {
        val list = ArrayList<Cover>()

        val html = getHtml(url)
        //log(html)
        val doc = Jsoup.parse(html)
        // var doc = Jsoup.connect(url).get()

        val elements = doc.select("ul.chinaMangaContentList").select("li")

        for (element in elements) {
            val coverUrl = if (element.select("img").attr("src").contains("http")) {
                element.select("img").attr("src")
            } else {
                "http://ishuhui.net" + element.select("img").attr("src")
            }

            val title = element.select("p").text()
            val link = "http://ishuhui.net" + element.select("div.chinaMangaContentImg").select("a").attr("href")

            val cover = Cover(coverUrl, title, link)
            list.add(cover)
        }

        return list
    }

}