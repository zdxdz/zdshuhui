package com.shetuans.zdshuhui.domain

import com.shetuans.zdshuhui.domain.News
import com.shetuans.zdshuhui.domain.NewsContainer
import com.shetuans.zdshuhui.domain.Source
import com.shetuans.zdshuhui.getHtml
import com.shetuans.zdshuhui.log
import org.jsoup.Jsoup
import java.util.*

/**
 * @author wupanjie
 */
class NewsSource : Source<ArrayList<NewsContainer>> {
    override fun obtain(url: String): ArrayList<NewsContainer> {
        val list = ArrayList<NewsContainer>()

        val html = getHtml(url)
        val doc = Jsoup.parse(html)

        val elements = doc.select("div.reportersBox").select("div.reportersMain")
        for (element in elements) {
            val title = element.select("div.mangeListTitle").select("span").text()
            val newsList = ArrayList<News>()

            for (ele in element.select("ul.reportersList").select("li").select("a")) {
                val title_news = ele.select("span")[0].text().replace("&amp", "\t")
                val createdTime = ele.select("span")[1].text()
                val link = "http://ishuhui.net/" + ele.attr("href")
                val news = News(title_news, createdTime, link)
                newsList.add(news)
            }

            val newsContainer = NewsContainer(title, newsList)

            list.add(newsContainer)
        }

        return list

    }

}