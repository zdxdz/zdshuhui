package com.shetuans.zdshuhui

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import com.shetuans.zdshuhui.ui.activity.AboutActivity
import com.shetuans.zdshuhui.ui.adapter.ContentPagerAdapter
import com.shetuans.zdshuhui.ui.fragment.BookFragment
import com.shetuans.zdshuhui.ui.fragment.HomeFragment
import com.shetuans.zdshuhui.ui.fragment.NewsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        val GITHUB_URL = "https://github.com/zdxdz/zdshuhui"
    }

    val titleResList: ArrayList<Int> = arrayListOf(R.string.tab_one, R.string.tab_two, R.string.tab_three)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    fun init() {
        setSupportActionBar(toolbar)

        val fragments = ArrayList<Fragment>()
        fragments.add(HomeFragment())
        fragments.add(BookFragment())
        fragments.add(NewsFragment())

        val titleList = titleResList.map { getString(it) }

        viewPager.adapter = ContentPagerAdapter(fragments, titleList, supportFragmentManager)
        viewPager.offscreenPageLimit = 2

        tabLayout.setupWithViewPager(viewPager)

        fab.setOnClickListener {
            val uri = Uri.parse(GITHUB_URL)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_about) {
            startActivity(Intent(this@MainActivity, AboutActivity().javaClass))
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
