package com.example.android.training.presenter.ui.cart

import android.content.ClipData.Item
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.training.databinding.FragmentCartBinding
import com.example.android.training.presenter.ui.cart.adapter.CartAdapter
import com.example.android.training.presenter.ui.cart.model.CartLayout
import com.example.android.training.presenter.ui.cart.model.CartManager
import com.google.gson.Gson

class CartFragment : Fragment(), CartAdapter.CartAdapterListener {
    private lateinit var cartItems: MutableList<CartLayout>
    private lateinit var adapter: CartAdapter

    var item: Item? = null
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        // Khởi tạo RecyclerView
        binding.listProduct.layoutManager = LinearLayoutManager(activity)

        // Lấy dữ liệu giỏ hàng
        cartItems = CartManager.getCartItems()
        // Khởi tạo adapter
        adapter = CartAdapter(cartItems, this)
        binding.listProduct.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Khôi phục danh sách sản phẩm trong giỏ hàng từ SharedPreferences
//        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
//        val cartItemsJson = sharedPreferences.getString("CART_ITEMS", null)
//        if (!cartItemsJson.isNullOrEmpty()) {
//            val cartItemsListType = object : TypeToken<List<String>>() {}.type
//            val cartItemsJsonList = Gson().fromJson<List<String>>(cartItemsJson, cartItemsListType)
//            cartItems = mutableListOf()
//            for (cartItemJson in cartItemsJsonList) {
//                val cartItem = cartItemJson.toCartLayout()
//                cartItems.add(cartItem)
//            }
//            adapter.setItems(cartItems)
//        }

    }

    override fun onStop() {
        super.onStop()

        // Lưu danh sách sản phẩm trong giỏ hàng vào SharedPreferences
//        val cartItemsJson = mutableListOf<String>()
//        cartItems.forEach { cartItem ->
//            cartItemsJson.add(cartItem.toJson())
//        }
//        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
//        with(sharedPreferences.edit()) {
//            putString("CART_ITEMS", Gson().toJson(cartItemsJson))
//            apply()
//        }
    }

    override fun onQuantityChanged(itemId: String, newQuantity: Int) {
        // Cập nhật số lượng sản phẩm trong giỏ hàng
        CartManager.updateItemQuantity(itemId, newQuantity)

        // Cập nhật lại danh sách sản phẩm trong adapter
        cartItems = CartManager.getCartItems()
        adapter.setItems(cartItems)
        //adapter.notifyDataSetChanged()
    }

    override fun onItemRemoved(itemId: String) {
        // Xóa sản phẩm khỏi giỏ hàng
        CartManager.removeItemFromCart(itemId)

        // Cập nhật lại danh sách sản phẩm trong adapter
        cartItems = CartManager.getCartItems()
        adapter.setItems(cartItems)
        //adapter.notifyDataSetChanged()
    }

    fun CartLayout.toJson(): String {
        return Gson().toJson(this)
    }

    fun String.toCartLayout(): CartLayout {
        return Gson().fromJson(this, CartLayout::class.java)
    }
}
