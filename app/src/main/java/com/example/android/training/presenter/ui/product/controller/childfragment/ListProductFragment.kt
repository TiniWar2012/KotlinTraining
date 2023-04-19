package com.example.android.training.presenter.ui.product.controller.childfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.epoxy.EpoxyAttribute
import com.example.android.training.R
import com.example.android.training.databinding.LayoutProductVerticalBinding
import com.example.android.training.presenter.ui.product.ProductViewModel
import com.example.android.training.presenter.ui.product.controller.ProductController
import com.example.android.training.presenter.ui.product.controller.model.ClickProduct
import com.example.android.training.presenter.ui.product.model.ProductLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListProductFragment : Fragment(), ClickProduct {
    private var _binding: LayoutProductVerticalBinding? = null
    private val productViewModel by viewModels<ProductViewModel>()
    private lateinit var productController: ProductController

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = LayoutProductVerticalBinding.inflate(inflater, container, false)
        productController = ProductController(itemClick = this)
        binding.recyclerView.adapter = productController.adapter
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        productViewModel.productViewStateLiveData.observe(viewLifecycleOwner) {
            productController.setData(it)
        }
        return binding.root
    }

    override fun onClickItem(productLayout: ProductLayout) {
        productViewModel.getDetailProduct(productLayout)
        val bundle = bundleOf("productLayout" to productLayout)
        findNavController().navigate(R.id.productDetailFragment_v2, bundle)
    }
}