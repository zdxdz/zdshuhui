package com.shetuans.zdshuhui.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

import android.support.v7.app.AppCompatActivity
import android.view.MenuItem

import com.shetuans.zdshuhui.domain.ComicSource
import com.shetuans.zdshuhui.R
import com.shetuans.zdshuhui.domain.Comic
import com.shetuans.zdshuhui.ui.fragment.ComicFragment
import kotlinx.android.synthetic.main.activity_comic.*
import org.jetbrains.anko.appcompat.v7.navigationIconResource
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by Administrator on 2017/5/31.
 */
class ComicActivity: AppCompatActivity() {

    companion object {
        val INTENT_COMIC_URL = "comic_url"
        val INTENT_COMIC_TITLE = "comic_title"
    }

    var mData = ArrayList<Comic>()
    lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 默认显示返回图片
        init()
    }

    private fun init() {
        val title = intent.getStringExtra(INTENT_COMIC_TITLE)
        supportActionBar?.title = title

        url = intent.getStringExtra(INTENT_COMIC_URL)

        doAsync {
            val data = ComicSource().obtain(url)

            uiThread {
                mData = data
                comicPagers.adapter = ComicPagerAdapter(mData, supportFragmentManager)
            }
        }
    }

    class ComicPagerAdapter(var items: ArrayList<Comic>, fm: FragmentManager): FragmentStatePagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return newFragment(items[position].url)
        }

        override fun getCount(): Int {
            return items.size
        }

        fun refreshData(newData: ArrayList<Comic>) {
            items = newData
            notifyDataSetChanged()
        }

        fun newFragment(url: String) = ComicFragment(url)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}