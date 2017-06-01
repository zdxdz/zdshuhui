package com.shetuans.zdshuhui.domain

import com.shetuans.zdshuhui.domain.Source
import com.shetuans.zdshuhui.getHtml
import org.jsoup.Jsoup

/**
 * Created by Flying SnowBean on 16-3-9.
 */
class SBSSource : Source<String> {
    override fun obtain(url: String): String {
        val html = getHtml(url)
        val doc = Jsoup.parse(html)

        //TODO Need To do better
        val contentHtml =
                "<html>${doc.select("head").toString()}<body>${doc.select("div.mangaContentMainImg").toString()}</body></html>"
        return contentHtml
    }

}