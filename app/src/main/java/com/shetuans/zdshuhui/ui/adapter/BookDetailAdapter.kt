package com.shetuans.zdshuhui.ui.adapter

import android.support.design.internal.NavigationMenuItemView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shetuans.zdshuhui.R
import com.shetuans.zdshuhui.domain.Page
import kotlinx.android.synthetic.main.item_page.view.*

/**
 * Created by Administrator on 2017/5/31.
 */
class BookDetailAdapter(var items: ArrayList<Page> = ArrayList(), val itemClick: (View, Int)->Unit) : RecyclerView.Adapter<BookDetailAdapter.BookDetailHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BookDetailHolder {
        return BookDetailHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_page, null ))
    }

    override fun onBindViewHolder(holder: BookDetailHolder, position: Int) {
        bindData(holder.itemView, position)
    }

    fun bindData(itemView: View, position: Int) {
        itemView.tv_page.text = items[position].title
        itemView.tv_page.setOnClickListener { itemClick(itemView, position) }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun refreshData(newData: ArrayList<Page>) {
        items = newData
        notifyDataSetChanged()
    }

    class BookDetailHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}