package com.shetuans.zdshuhui.domain

import com.shetuans.zdshuhui.getHtml
import com.shetuans.zdshuhui.log
import org.jsoup.Jsoup

/**
 * Created by Administrator on 2017/5/31.
 */
class CoverSource: Source<ArrayList<Cover>> {

    override fun obtain(url: String): ArrayList<Cover> {

        val html = getHtml(url)
        val doc = Jsoup.parse(html)
        log(doc)

        val result: ArrayList<Cover> = ArrayList()

        val elements = doc.select("ul.mangeListBox").select("li")
        for (element in elements) {
            val coverUrl = element.select("img").attr("src")
            val title = element.select("h1").text() + "-\n" + element.select("h2").text()
            val link = "http://ishuhui.net" + element.select("div.magesPhoto").select("a").attr("href")
            val cover = Cover(coverUrl, title, link)
            result.add(cover)
        }

        return result
    }

}