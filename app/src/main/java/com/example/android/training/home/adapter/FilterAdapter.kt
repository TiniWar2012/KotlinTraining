package com.example.android.training.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.android.training.home.model.HomeFilterLayout
import com.example.android.training.R

class FilterAdapter() : RecyclerView.Adapter<FilterAdapter.FilterListHolder>() {
    private val filterList: MutableList<HomeFilterLayout> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
//    fun setData(listFilter: List<HomeFilterLayout>) {
    fun setData() {
//        filterList.addAll(listFilter)
        filterList.add(HomeFilterLayout("https://i.imgur.com/MTIhoBu.png", "Coupons"))
        filterList.add(HomeFilterLayout("https://i.imgur.com/KrVXY8q.png", "Shop by Brands"))
        filterList.add(HomeFilterLayout("https://i.imgur.com/GY0Oivg.png", "Shop by Store"))
        filterList.add(HomeFilterLayout("https://i.imgur.com/vY96MH8.png", "Shop by Product"))
        filterList.add(HomeFilterLayout("https://i.imgur.com/P8Wpf3u.png", "Beauty"))
        filterList.add(HomeFilterLayout("https://i.imgur.com/v4Oy7lA.png", "Women"))
        filterList.add(HomeFilterLayout("https://i.imgur.com/1E4dcgS.png", "Man"))
        filterList.add(HomeFilterLayout("https://i.imgur.com/OAIhcCj.png", "Kids"))
        filterList.add(HomeFilterLayout("https://i.imgur.com/DGTavMl.png", "Home"))
        filterList.add(HomeFilterLayout("https://i.imgur.com/8vp64Co.png", "Tech"))
        filterList.add(HomeFilterLayout("https://i.imgur.com/qlOiabU.png", "Sport - Travel -"))
        filterList.add(HomeFilterLayout("https://i.imgur.com/PLifqv5.png", "Gift"))
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterListHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_filterlist, parent, false)
        return FilterListHolder(view)
    }

    override fun onBindViewHolder(holder: FilterListHolder, position: Int) {

        val filter = filterList[position]
        Glide.with(holder.iconFilter.context).load(filter.images).diskCacheStrategy(
            DiskCacheStrategy.ALL
        ).into(holder.iconFilter)
        holder.titleicoBrand.text = filter.titles

    }

    override fun getItemCount(): Int {
        return filterList.size
    }

    class FilterListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iconFilter: ImageView = itemView.findViewById(R.id.icoFilter)
        val titleicoBrand: TextView = itemView.findViewById(R.id.titleFilter)
    }
}