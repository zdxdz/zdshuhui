package com.shetuans.zdshuhui.ui.view

import android.content.Context
import android.support.design.widget.BottomSheetDialog
import android.view.LayoutInflater
import android.webkit.WebSettings
import android.webkit.WebView
import com.shetuans.zdshuhui.domain.NewsDetailSource
import com.shetuans.zdshuhui.R
import com.shetuans.zdshuhui.domain.News
import com.shetuans.zdshuhui.domain.Source
import com.shetuans.zdshuhui.load
import com.shetuans.zdshuhui.log
import kotlinx.android.synthetic.main.dialog_web_detail.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by Administrator on 2017/5/31.
 */
class WebViewDialog(val context: Context,val title: String, val url: String) {
    val dialog = BottomSheetDialog(context)

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_web_detail, null)
        val refresh = view.newsDetailRefresh
        val webView = view.webView
        view.titleBar.subtitle = title

        webView.settings.textZoom = 80
        webView.settings.displayZoomControls = true
        webView.settings.setSupportZoom(true)
        webView.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN

        webView.loadUrl(url)

        dialog.setContentView(view)


        refresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorPrimaryDark)
        refresh.setOnRefreshListener {
            webView.loadUrl(url)
            refresh.isRefreshing = false
        }

        dialog.show()
    }
}