package com.example.android.training.presenter.ui.home.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.training.R
import com.example.android.training.presenter.ui.home.model.HomeProductLayout

//import com.example.android.training.home.model.HomeProductLayoutNew

class ProductAdapter(
    private val onClick: HandleClickListener,
    private val itemClick: ItemClickListener
) :
    RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {
    //    private val productList: MutableList<HomeProductLayoutNew> = mutableListOf()
    private val productList: MutableList<HomeProductLayout> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
//    fun setData(listProduct: List<HomeProductLayoutNew>) {
    fun setData(listProduct: List<HomeProductLayout>) {
        productList.addAll(listProduct)
        notifyDataSetChanged()
    }

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("ResourceAsColor", "SetTextI18n")
        fun bind(
            product: HomeProductLayout,
            onClick: HandleClickListener,
            itemClick: ItemClickListener
        ) {
            val title = view.findViewById<TextView>(R.id.title)
            val desciption = view.findViewById<TextView>(R.id.description)
            val price = view.findViewById<TextView>(R.id.price)
            val imageView = view.findViewById<ImageView>(R.id.imageView)
            val like = view.findViewById<ImageButton>(R.id.button_favorite)


            title.text = product.title.uppercase()
            desciption.text = product.description.uppercase()
            price.text = "฿ ${product.price}"
            Glide.with(view.context).load(product.image).placeholder(R.drawable.img_loading_icon)
                .centerCrop().into(imageView)

            if (product.like) {
                like.setImageResource(R.drawable.ic_favorite)
                Log.d("--->", "true")
            } else {
                like.setImageResource(R.drawable.ic_unfavorite)
                Log.d("--->", "false")
            }
            like.setOnClickListener {
                if (product.like) {
                    product.like = false
                    like.setImageResource(R.drawable.ic_unfavorite)
                    onClick.onClick(product)
                } else {
                    product.like = true
                    like.setImageResource(R.drawable.ic_favorite)
                    onClick.onClick(product)
                }
            }
            view.setOnClickListener {
                itemClick.onClickItem(product)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(
            productList[position],
            onClick,
            itemClick
        )
    }
}

interface HandleClickListener {
    //    fun onClick(homeProductLayoutNew: HomeProductLayoutNew)
    fun onClick(homeProductLayout: HomeProductLayout)
}

interface ItemClickListener {
    //    fun onClickItem(homeProductLayoutNew: HomeProductLayoutNew)
    fun onClickItem(homeProductLayout: HomeProductLayout)
}
