package com.example.android.training.presenter.ui.detail

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.android.training.R
import com.example.android.training.databinding.FragmentDetailProductV2Binding
import com.example.android.training.presenter.ui.product.model.ProductLayout

class ProductDetailFragment_v2 : Fragment() {
    private var _binding: FragmentDetailProductV2Binding? = null
    private val binding get() = _binding!!

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
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        return binding.root
    }
}