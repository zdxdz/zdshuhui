package com.shetuans.zdshuhui.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shetuans.zdshuhui.R
import com.shetuans.zdshuhui.domain.Cover
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home.view.*

/**
 * Created by Administrator on 2017/5/31.
 */
class CoverAdapter(var items: List<Cover>, val itemClick: (View, Int)->Unit) : RecyclerView.Adapter<CoverAdapter.HomeHolder>() {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: HomeHolder?, position: Int) {
        holder?.bind(items[position], position)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HomeHolder {

        return HomeHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_home, null), itemClick)
    }

    class HomeHolder(itemView: View?, val itemClick: (View, Int) -> Unit) : RecyclerView.ViewHolder(itemView) {

        fun bind( cover: Cover , position: Int) {

            itemView.tv_cover.text = cover.title
            Picasso.with(itemView.context).load(cover.coverUrl).into(itemView.iv_cover)
            itemView.iv_cover.setOnClickListener {
                itemClick(itemView, position)
            }
        }

    }

    fun refreshData(newData: List<Cover>) {
        items = newData
        notifyDataSetChanged()
    }

}