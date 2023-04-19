package com.example.android.training.presenter.ui.cart.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.training.R
import com.example.android.training.base.ButtonAddToCartView
import com.example.android.training.base.HandleQuantity
import com.example.android.training.presenter.ui.cart.model.CartLayout

class CartAdapter(
    private var cartItems: List<CartLayout>, private val listener: CartAdapterListener
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cartItem = cartItems[position]
        holder.bind(cartItem)
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    fun setItems(items: MutableList<CartLayout>) {
        this.cartItems = items
        notifyDataSetChanged()
    }

    class ViewHolder(val view : View, private val listener: CartAdapterListener) :
        RecyclerView.ViewHolder(view), HandleQuantity {
        lateinit var productId: String

        fun bind(cartItem: CartLayout) {
            // Xử lý hiển thị thông tin sản phẩm trong giỏ hàng
            // Set sự kiện tương tác khi thay đổi số lượng sản phẩm
            productId = cartItem.id
            itemView.findViewById<TextView>(R.id.brandProduct).text = cartItem.id
            itemView.findViewById<TextView>(R.id.nameProduct).text = cartItem.title
            itemView.findViewById<TextView>(R.id.descriptionProduct).text = cartItem.description
            itemView.findViewById<TextView>(R.id.discountPrice).text = cartItem.price.toString()
            itemView.findViewById<TextView>(R.id.oldPrice)
            itemView.findViewById<TextView>(R.id.quantity).text = cartItem.quantity.toString()
            itemView.findViewById<TextView>(R.id.sumPrice)
            Glide.with(view.context).load(cartItem.thumbnail).placeholder(R.drawable.img_loading_icon)
                .centerCrop().into(view.findViewById(R.id.photoProduct))
            itemView.findViewById<ButtonAddToCartView>(R.id.buttonCounter).setListener(this)


            // Set sự kiện tương tác khi xóa sản phẩm khỏi giỏ hàng
//            removeButton.setOnClickListener {
//                listener.onItemRemoved(cartItem.id)
//            }
        }

        override fun onQuantityChanged(quantity: Int) {
            listener.onQuantityChanged(productId, quantity)
        }
    }

    interface CartAdapterListener {
        fun onQuantityChanged(itemId: String, newQuantity: Int)
        fun onItemRemoved(itemId: String)
    }
}
