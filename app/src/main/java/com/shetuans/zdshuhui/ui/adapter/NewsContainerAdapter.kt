package com.shetuans.zdshuhui.ui.adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shetuans.zdshuhui.R
import com.shetuans.zdshuhui.domain.News
import com.shetuans.zdshuhui.domain.NewsContainer
import kotlinx.android.synthetic.main.item_news.view.*

/**
 * Created by Administrator on 2017/6/1.
 */
class NewsContainerAdapter(var items: ArrayList<NewsContainer> = ArrayList(), val itemClick: (View, Int)->Unit): RecyclerView.Adapter<NewsContainerAdapter.NewsContainerHolder>() {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewsContainerHolder, position: Int) {
        bindData(holder.itemView, position)
    }

    private fun bindData(itemView: View, position: Int) {
        itemView.tv_container_title.text = items[position].title
        itemView.rv_child_container.layoutManager = LinearLayoutManager(itemView.context)
        itemView.rv_child_container.adapter = NewsAdapter(items[position].newsList as java.util.ArrayList<News>)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NewsContainerHolder {
        return NewsContainerHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_news, null))
    }

    fun refreshData(newData: ArrayList<NewsContainer>) {
        items = newData
        notifyDataSetChanged()
    }

    class NewsContainerHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}


