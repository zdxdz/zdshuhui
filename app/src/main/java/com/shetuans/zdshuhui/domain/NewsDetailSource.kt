package com.shetuans.zdshuhui.domain

import com.shetuans.zdshuhui.getHtml
import org.jsoup.Jsoup

/**
 * Created by Flying SnowBean on 16-3-9.
 */
class NewsDetailSource() : Source<String> {
    override fun obtain(url: String): String {
        val html = getHtml(url)
        val doc = Jsoup.parse(html)

        val contentHtml =
                "<html>${doc.select("head").toString()}<body>${doc.select("div.featureContentText").toString()}</body></html>"
        return contentHtml
    }

}