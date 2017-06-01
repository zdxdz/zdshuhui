package com.shetuans.zdshuhui.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shetuans.zdshuhui.R
import com.shetuans.zdshuhui.domain.BookSource
import com.shetuans.zdshuhui.domain.Cover
import com.shetuans.zdshuhui.ui.activity.BookDetailActivity
import com.shetuans.zdshuhui.ui.adapter.CoverAdapter
import kotlinx.android.synthetic.main.fragment_book.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by Administrator on 2017/5/31.
 */
class BookFragment: Fragment() {

    companion object{
        val AIM_URL = "http://ishuhui.net/ComicBookList/"
    }

    var mData = ArrayList<Cover>()
    lateinit var adapter: CoverAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_book, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            load()
        }
    }

    private fun initView() {

        bookList.layoutManager = GridLayoutManager(activity, 2)
        adapter = CoverAdapter(mData, {view, i -> jump2Detail(i) })
        bookList.adapter = adapter

        bookRefresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorPrimaryDark)
        bookRefresh.setOnRefreshListener {
            load()
        }
        bookRefresh.post { bookRefresh.isRefreshing = true }
    }

    private fun load() {
        doAsync {

            var data = BookSource().obtain(AIM_URL)
            uiThread {
                mData = data
                adapter.refreshData(mData)
                bookRefresh.isRefreshing = false
            }
        }
    }

    private fun  jump2Detail(position: Int) {
        var intent = Intent(context, BookDetailActivity().javaClass)

        intent.putExtra(BookDetailActivity.INTENT_COVER_URL, mData[position].coverUrl)
        intent.putExtra(BookDetailActivity.INTENT_URL, mData[position].link)
        intent.putExtra(BookDetailActivity.INTENT_TITLE, mData[position].title)
        startActivity(intent)
    }
}