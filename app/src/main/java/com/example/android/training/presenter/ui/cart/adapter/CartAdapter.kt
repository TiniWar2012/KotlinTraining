package com.example.android.training.presenter.ui.cart.adapter

import android.annotation.SuppressLint
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.strikeThrough
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.training.R
import com.example.android.training.base.ButtonAddToCartView
import com.example.android.training.base.HandleQuantity
import com.example.android.training.presenter.ui.cart.model.CartLayout
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject

@SuppressLint("NotifyDataSetChanged")
open class CartAdapter(
 query: Query
) : FirestoreAdapter<CartAdapter.ViewHolder>(query) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getSnapshot(position).toObject<CartLayout>())
    }

//    fun setItems(items: MutableList<CartLayout>) {
//        this.cartItems = items
//        notifyDataSetChanged()
//    }
//
//    fun addItem(item: CartLayout) {
//        this.cartItems.add(item)
//        notifyDataSetChanged()
//    }

    class ViewHolder(val view: View) :
        RecyclerView.ViewHolder(view), HandleQuantity {
        lateinit var productId: String

        @SuppressLint("SetTextI18n")
        fun bind(data: CartLayout?) {
            // Xử lý hiển thị thông tin sản phẩm trong giỏ hàng
            // Set sự kiện tương tác khi thay đổi số lượng sản phẩm
            data?.let { cartItem ->
                productId = cartItem.id
                itemView.findViewById<TextView>(R.id.brandProduct).text =
                    "Sold by ${cartItem.brand}"
                itemView.findViewById<TextView>(R.id.nameProduct).text = cartItem.title
                itemView.findViewById<TextView>(R.id.descriptionProduct).text = cartItem.description
                itemView.findViewById<TextView>(R.id.discountPrice).text = cartItem.price.toString()
                val priceold = itemView.findViewById<TextView>(R.id.oldPrice)
                if (cartItem.discountPercentage.toFloat() > 0) {
                    priceold.text = SpannableStringBuilder().strikeThrough {
                        append("฿ ${cartItem.price - (cartItem.price * (cartItem.discountPercentage / 100))}")
                    }
                    priceold.isVisible = true
                } else {
                    priceold.isVisible = false
                }
                itemView.findViewById<TextView>(R.id.quantity).text = cartItem.quantity.toString()
                itemView.findViewById<TextView>(R.id.sumPrice).text =
                    "฿ ${cartItem.price * cartItem.quantity}"
                Glide.with(view.context).load(cartItem.thumbnail)
                    .placeholder(R.drawable.img_loading_icon).centerCrop()
                    .into(view.findViewById(R.id.photoProduct))
                itemView.findViewById<ButtonAddToCartView>(R.id.buttonCounter).setListener(this)
            }
            // Set sự kiện tương tác khi xóa sản phẩm khỏi giỏ hàng
//            removeButton.setOnClickListener {
//                listener.onItemRemoved(cartItem.id)
//            }
        }

        override fun setQuantity(quantity: Int) {
            TODO("Not yet implemented")
        }
    }
}
