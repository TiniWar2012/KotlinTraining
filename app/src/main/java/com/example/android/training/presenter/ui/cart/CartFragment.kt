package com.example.android.training.presenter.ui.cart

import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.training.R
import com.example.android.training.databinding.FragmentCartBinding
import com.example.android.training.presenter.ui.cart.adapter.CartAdapter
import com.example.android.training.presenter.ui.cart.model.CartLayout
import com.example.android.training.presenter.ui.cart.model.CartManager
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CartFragment : Fragment() {
    private lateinit var cartItems: MutableList<CartLayout>
    private lateinit var adapter: CartAdapter
    private lateinit var firestore: FirebaseFirestore
    private var query: Query? = null


    var item: Item? = null
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
//        // Khởi tạo RecyclerView
//        cartItems = CartManager.getCartItems()
//        // Lấy dữ liệu giỏ hàng
//        cartItems = CartManager.getCartItems()
//        // Khởi tạo adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firestore = Firebase.firestore
        // Khởi tạo RecyclerView
        cartItems = CartManager.getCartItems()
        query = firestore.collection("Cart")
        query?.let {
            adapter = object : CartAdapter(it) {
                override fun onDataChanged() {
                    // Show/hide content if the query returns empty.
                    if (itemCount == 0) {
                        binding.listProduct.visibility = View.GONE
                        binding.cartEmpty.cartEmptyLayout.visibility = View.VISIBLE
                    } else {
                        binding.listProduct.visibility = View.VISIBLE
                        binding.cartEmpty.cartEmptyLayout.visibility = View.GONE
                    }
                }

                override fun onError(e: FirebaseFirestoreException) {
                    // Show a snackbar on errors
                    Snackbar.make(
                        binding.root, "Error: check logs for info.", Snackbar.LENGTH_LONG
                    ).show()
                }
            }
            binding.listProduct.adapter = adapter
        }
        binding.listProduct.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.cartEmpty.buttonShopping.setOnClickListener {
            findNavController().popBackStack(R.id.productDetailFragment_v2, true)
        }
    }

    override fun onStart() {
        super.onStart()
        // Start listening for Firestore updates
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }
}
