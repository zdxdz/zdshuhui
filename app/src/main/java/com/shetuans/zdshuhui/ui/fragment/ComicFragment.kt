package com.shetuans.zdshuhui.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shetuans.zdshuhui.R
import com.shetuans.zdshuhui.snackbar
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_comic_page.*

/**
 * Created by Administrator on 2017/5/31.
 */
class ComicFragment(val url: String): Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_comic_page, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pb_comic.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        Picasso.with(activity)
                .load(url)
                .placeholder(R.color.material_deep_purple_50)
                .into(iv_comic, object : Callback{
                    override fun onSuccess() {
                        pb_comic.visibility = View.GONE
                    }

                    override fun onError() {
                        iv_comic.snackbar(getString(R.string.network_error))
                    }

                })
    }
}