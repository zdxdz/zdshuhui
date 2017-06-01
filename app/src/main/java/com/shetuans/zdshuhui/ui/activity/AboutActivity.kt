package com.shetuans.zdshuhui.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

import com.shetuans.zdshuhui.R
import com.shetuans.zdshuhui.domain.News
import com.shetuans.zdshuhui.domain.NewsDetailSource
import com.shetuans.zdshuhui.domain.NewsSource
import com.shetuans.zdshuhui.ui.view.WebDetailDialog
import com.shetuans.zdshuhui.ui.view.WebViewDialog
import kotlinx.android.synthetic.main.activity_about.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        init()
    }

    private fun init() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "reborn"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        home.onClick {
            WebViewDialog(this@AboutActivity, "我的主页", "https://github.com/zdxdz")
        }
        project.onClick {
            WebViewDialog(this@AboutActivity,"项目主页","https://github.com/zdxdz/zdshuhui")
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
