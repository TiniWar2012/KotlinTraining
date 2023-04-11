package com.example.android.training.presenter.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.training.R
import com.example.android.training.presenter.ui.home.model.HomeBannerLayout

class HeaderBannerAdapter : RecyclerView.Adapter<HeaderBannerAdapter.MyViewHolder>() {
    private val bannerList: MutableList<HomeBannerLayout> = mutableListOf()

//    private val images = mutableListOf<String>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listBanner: List<HomeBannerLayout>) {
        bannerList.addAll(listBanner)
        notifyDataSetChanged()
    }

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val imageView = view.findViewById<ImageView>(R.id.img_banner)
//        fun bind(url: String) {
        fun bind(photo: HomeBannerLayout) {
            Glide.with(view.context).load(photo.getImageUrl())
                .placeholder(R.drawable.img_loading_icon).centerCrop().into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.items_banner_image, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.bind(images[position])
        holder.bind(bannerList[position])
    }

    override fun getItemCount(): Int {
        return bannerList.size
    }
}