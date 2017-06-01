package com.shetuans.zdshuhui.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v7.widget.GridLayoutManager
import android.view.MenuItem
import com.shetuans.zdshuhui.domain.BookDetailSource
import com.shetuans.zdshuhui.domain.SBSSource

import com.shetuans.zdshuhui.R
import com.shetuans.zdshuhui.domain.BookDetail
import com.shetuans.zdshuhui.domain.BookSource
import com.shetuans.zdshuhui.domain.News
import com.shetuans.zdshuhui.domain.Page
import com.shetuans.zdshuhui.log
import com.shetuans.zdshuhui.ui.adapter.BookDetailAdapter
import com.shetuans.zdshuhui.ui.view.WebDetailDialog
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_book_detail.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.pagerTabStrip
import org.jetbrains.anko.uiThread

class BookDetailActivity : AppCompatActivity() {

    companion object{
        val INTENT_COVER_URL = "cover_url"
        val INTENT_URL = "url"
        val INTENT_TITLE = "title"
    }
    lateinit var url: String
    lateinit var adapter: BookDetailAdapter
    lateinit var bookDetail: BookDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)
        setSupportActionBar(toolbar)
        init()
    }

    override fun onResume() {
        super.onResume()
        pageRefresh.post { pageRefresh.isRefreshing = true }
        load()
    }

    private fun init() {
        val coverUrl = intent.getStringExtra(INTENT_COVER_URL)
        val title = intent.getStringExtra(INTENT_TITLE)
        url = intent.getStringExtra(INTENT_URL)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = title

        Picasso.with(this).load(coverUrl).into(iv_background)

        pageList.layoutManager = GridLayoutManager(this, 4)
        adapter = BookDetailAdapter {view, i -> if (title.contains("SBS")) {
                val news = News(bookDetail[i].title, "", bookDetail[i].link)
                WebDetailDialog(this, news, SBSSource())
            } else {
                jump2Read(i)
            }
        }
        pageList.adapter = adapter

        pageRefresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorPrimaryDark)
        pageRefresh.setOnRefreshListener {
            load()
        }
    }

    private fun jump2Read(position: Int) {
        var intent = Intent(this, ComicActivity().javaClass)
        intent.putExtra(ComicActivity.INTENT_COMIC_URL, bookDetail[position].link)
        intent.putExtra(ComicActivity.INTENT_COMIC_TITLE, bookDetail[position].title)
        startActivity(intent)
    }

    private fun load() = doAsync {

        val data = BookDetailSource().obtain(url)
        uiThread {
            bookDetail = data
            val pages = bookDetail.pages as ArrayList<Page>
            adapter.refreshData(pages)
            pageRefresh.isRefreshing = false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
