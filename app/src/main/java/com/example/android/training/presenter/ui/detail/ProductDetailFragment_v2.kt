package com.example.android.training.presenter.ui.detail

import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.android.training.R
import com.example.android.training.databinding.FragmentDetailProductV2Binding
import com.example.android.training.presenter.ui.product.model.ProductLayout
import java.util.*

class ProductDetailFragment_v2 : Fragment() {
    private var _binding: FragmentDetailProductV2Binding? = null
    private val binding get() = _binding!!

    private var currentPage = 0
    private var timer: Timer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailProductV2Binding.inflate(inflater, container, false)
        val productLayout = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("productLayout", ProductLayout::class.java)
        } else {
            arguments?.getParcelable("productLayout")
        }
        binding.content.apply {
            productId.text = productLayout?.id.toString()
            descriptionProduct.text = productLayout?.description.toString()
            priceProduct.text = getString(R.string.price, productLayout?.price.toString())
            titleProduct.text = productLayout?.title.toString()
            ratingBar.rating = productLayout?.rating!!.toFloat()
        }

//        val photoProductAdapter = PhotoProductAdapter()
//        binding.content.productPhoto.adapter = photoProductAdapter
//        productLayout?.let {
//            photoProductAdapter.setData(productLayout.images)
//        }


        val photoProductAdapter = PhotoProductAdapter()

        val viewPager = binding.content.productPhoto
        val tabDots = binding.content.tabDots

        productLayout?.let {
            photoProductAdapter.setData(productLayout.images)
        }
        viewPager.adapter = photoProductAdapter
        tabDots.setViewPager(viewPager)
        // Set up automatic image sliding
        val handler = android.os.Handler(Looper.getMainLooper())
        val update = Runnable {
            if (productLayout != null) {
                if (currentPage == productLayout.images.size) {
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
                    val pageCount = productLayout!!.images.size
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack1.setOnClickListener {
            findNavController().popBackStack()
        }
//        binding.content.productPhoto.adapter =
    }
    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
        timer = null
    }
}