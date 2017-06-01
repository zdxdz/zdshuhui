package com.shetuans.zdshuhui.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shetuans.zdshuhui.domain.NewsDetailSource
import com.shetuans.zdshuhui.domain.SBSSource
import com.shetuans.zdshuhui.R
import com.shetuans.zdshuhui.domain.News
import com.shetuans.zdshuhui.ui.view.WebDetailDialog
import kotlinx.android.synthetic.main.item_child_news.view.*
import java.util.ArrayList

/**
 * Created by Administrator on 2017/6/1.
 */
class NewsAdapter(val items:ArrayList<News>) : RecyclerView.Adapter<NewsAdapter.NewsHolder>() {
    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        bindData(holder.itemView, position)
    }

    private fun  bindData(itemView: View, position: Int) {
        itemView.tv_title.text = items[position].title
        itemView.tv_time.text = items[position].createdTime

        itemView.container.setOnClickListener {
            WebDetailDialog(itemView.context, items[position], NewsDetailSource())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NewsHolder {
       return NewsHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_child_news, null))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}