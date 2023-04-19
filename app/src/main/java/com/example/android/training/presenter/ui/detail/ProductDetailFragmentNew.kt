package com.example.android.training.presenter.ui.detail

import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.android.training.R
import com.example.android.training.databinding.FragmentDetailProductV2Binding
import com.example.android.training.presenter.ui.cart.CartViewModel
import com.example.android.training.presenter.ui.cart.model.CartManager
import com.example.android.training.presenter.ui.product.model.ProductLayout
import java.util.*

@Suppress("DEPRECATION")
class ProductDetailFragmentNew : Fragment() {
    private var _binding: FragmentDetailProductV2Binding? = null
    private val binding get() = _binding!!
    private val cartViewModel by viewModels<CartViewModel>()
    private var currentPage = 0
    private var timer: Timer? = null
    private var product: ProductLayout? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailProductV2Binding.inflate(inflater, container, false)
        product = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("productLayout", ProductLayout::class.java)
        } else {
            arguments?.getParcelable("productLayout")
        }
        binding.content.apply {
            productId.text = product?.id.toString()
            descriptionProduct.text = product?.description.toString()
            priceNew.text = getString(R.string.price, product?.price.toString())
            titleProduct.text = product?.title.toString()
            ratingBar.rating = product?.rating!!.toFloat()
        }
        val photoProductAdapter = PhotoProductAdapter()
        val viewPager = binding.content.productPhoto
        val tabDots = binding.content.tabDots

        product?.let {
            photoProductAdapter.setData(it.images)
        }
        viewPager.adapter = photoProductAdapter
        tabDots.setViewPager(viewPager)
        // Set up automatic image sliding
        val handler = android.os.Handler(Looper.getMainLooper())
        val update = Runnable {
            if (product != null) {
                if (currentPage == product!!.images.size) {
                    currentPage = 0
                }
            }
            viewPager.setCurrentItem(currentPage++, true)
        }
        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, 1500, 1500)

        // Set up infinite scrolling
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    val pageCount = product!!.images.size
                    if (currentPage == 0) {
                        viewPager.setCurrentItem(pageCount - 2, false)
                    } else if (currentPage == pageCount - 1) {
                        viewPager.setCurrentItem(1, false)
                    }
                }
            }

            override fun onPageSelected(position: Int) {
                currentPage = position
            }
        })
        binding.btnBag.setOnClickListener {
            findNavController().navigate(R.id.action_productDetailFragment_v2_to_cartFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack1.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.action.btnAddToBag.setOnClickListener {
            product?.let {
                addToCart(it)
            }
        }
//        binding.content.productPhoto.adapter =
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
        timer = null
    }

    private fun addToCart(
        productLayout: ProductLayout
    ) {
//        val itemId = binding.content.productId.text.toString()
//        val itemPrice = binding.content.priceNew.text.toString().handlePrice()
//        val cartItem = CartLayout(itemId, itemPrice, 1)
        CartManager.addItemToCart(productLayout)
    }
}

// ฿ 1249
// [฿, 1249]
fun String.handlePrice(): Int {
    val text = this.split(" ")[1]
    return text.toInt()
}
