package com.shetuans.zdshuhui.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shetuans.zdshuhui.domain.NewsSource
import com.shetuans.zdshuhui.R
import com.shetuans.zdshuhui.domain.NewsContainer
import com.shetuans.zdshuhui.log
import com.shetuans.zdshuhui.ui.adapter.NewsContainerAdapter
import kotlinx.android.synthetic.main.fragment_news.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by Administrator on 2017/5/31.
 */
class NewsFragment: Fragment() {
    companion object {
        val AIM_URL = "http://ishuhui.net/CMS/"
    }

    var mData = ArrayList<NewsContainer>()
    lateinit var adapter: NewsContainerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            load()
        }
    }

    private fun init() {

        newsList.layoutManager = LinearLayoutManager(activity)
        adapter = NewsContainerAdapter() {view, i ->  }
        newsList.adapter = adapter

        newsRefresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorPrimaryDark)
        newsRefresh.setOnRefreshListener {
            load()
        }
        newsRefresh.post { newsRefresh.isRefreshing = true }
    }

    private fun load() {
        doAsync {
            val data = NewsSource().obtain(AIM_URL)

            uiThread {
                mData = data
                adapter.refreshData(mData)
                newsRefresh.isRefreshing = false
            }
        }
    }
}