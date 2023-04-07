package com.example.android.training.presenter.ui.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.android.training.R
import com.example.android.training.presenter.ui.home.model.HomeFilterLayout

class FilterAdapter() : RecyclerView.Adapter<FilterAdapter.FilterListHolder>() {
    private val filterList: MutableList<HomeFilterLayout> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
//    fun setData(listFilter: List<HomeFilterLayout>) {
    fun setData(context: Context) {
//        filterList.addAll(listFilter)
        filterList.add(
            HomeFilterLayout(
                "https://i.imgur.com/MTIhoBu.png", context.getString(R.string.filter_coupons)
            )
        )
        filterList.add(
            HomeFilterLayout(
                "https://i.imgur.com/KrVXY8q.png", context.getString(R.string.filter_shop_by_brand)
            )
        )
        filterList.add(
            HomeFilterLayout(
                "https://i.imgur.com/GY0Oivg.png", context.getString(R.string.filter_shop_by_store)
            )
        )
        filterList.add(
            HomeFilterLayout(
                "https://i.imgur.com/vY96MH8.png",
                context.getString(R.string.filter_shop_by_product)
            )
        )
        filterList.add(
            HomeFilterLayout(
                "https://i.imgur.com/P8Wpf3u.png", context.getString(R.string.filter_beauty)
            )
        )
        filterList.add(
            HomeFilterLayout(
                "https://i.imgur.com/v4Oy7lA.png", context.getString(R.string.filter_women)
            )
        )
        filterList.add(
            HomeFilterLayout(
                "https://i.imgur.com/1E4dcgS.png", context.getString(R.string.filter_men)
            )
        )
        filterList.add(
            HomeFilterLayout(
                "https://i.imgur.com/OAIhcCj.png", context.getString(R.string.filter_kids)
            )
        )
        filterList.add(
            HomeFilterLayout(
                "https://i.imgur.com/DGTavMl.png", context.getString(R.string.filter_home)
            )
        )
        filterList.add(
            HomeFilterLayout(
                "https://i.imgur.com/8vp64Co.png", context.getString(R.string.filter_tech)
            )
        )
        filterList.add(
            HomeFilterLayout(
                "https://i.imgur.com/qlOiabU.png", context.getString(R.string.filter_sport_travel)
            )
        )
        filterList.add(
            HomeFilterLayout(
                "https://i.imgur.com/PLifqv5.png", context.getString(R.string.filter_gift)
            )
        )
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