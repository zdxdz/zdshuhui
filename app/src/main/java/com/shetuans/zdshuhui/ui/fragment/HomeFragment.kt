package com.shetuans.zdshuhui.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shetuans.zdshuhui.R
import com.shetuans.zdshuhui.domain.Cover
import com.shetuans.zdshuhui.domain.CoverSource
import com.shetuans.zdshuhui.ui.activity.ComicActivity
import com.shetuans.zdshuhui.ui.adapter.CoverAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.uiThread

/**
 * Created by Administrator on 2017/5/28.
 */
class HomeFragment : Fragment() {

    companion object{
        val AIM_URL = "http://ishuhui.net/?PageIndex=1"
    }

    lateinit var mData: List<Cover>
    lateinit var adapter: CoverAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater?.inflate(R.layout.fragment_home, null)
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

    fun initView() {

        mData = ArrayList()
        homeList.layoutManager = GridLayoutManager(activity, 2)
        adapter = CoverAdapter(mData, {view, i -> jump2Comic(i) })
        homeList.adapter = adapter

        homeRefresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorPrimaryDark)
        homeRefresh.setOnRefreshListener {
            load()
        }
        // 执行刷新
        homeRefresh.post { homeRefresh.isRefreshing = true }

    }

    private fun load() {

        doAsync {
            val data = CoverSource().obtain(AIM_URL)
            uiThread {
                mData = data
                adapter.refreshData(mData)
                homeRefresh.isRefreshing = false
            }
        }

    }

    private fun  jump2Comic(position: Int) {
        val intent = Intent(activity, ComicActivity().javaClass)
        intent.putExtra(ComicActivity.INTENT_COMIC_URL, mData[position].link)
        intent.putExtra(ComicActivity.INTENT_COMIC_TITLE, mData[position].title.substring(0, mData[position].title.indexOf("-")))
        startActivity(intent)
    }
}