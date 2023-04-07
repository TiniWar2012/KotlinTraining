package com.example.android.training.presenter.ui.detail_product

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.android.training.R
import com.example.android.training.databinding.FragmentDetailProductBinding
import com.example.android.training.home.presenter.HomePresenter
import com.example.android.training.home.presenter.HomePresenterImp
import com.example.android.training.home.presenter.HomeView
import com.example.android.training.presenter.ui.home.model.HomeProductLayout

@Suppress("DEPRECATION")
class DetailProductFragment : Fragment(), HomeView {

    private var mHomePresenter: HomePresenter? = null

    private var _binding: FragmentDetailProductBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailProductBinding.inflate(inflater, container, false)
        val homeProductLayout = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("homeProductLayout", HomeProductLayout::class.java)
        } else {
            arguments?.getParcelable("homeProductLayout")
        }
        // call API get detail
        binding.content.apply {
            Glide.with(productPhoto).load(homeProductLayout?.image)
                .placeholder(R.drawable.img_loading_icon)
                .centerCrop().into(productPhoto)
            productId.text = homeProductLayout?.id.toString()
            descriptionProduct.text = homeProductLayout?.description.toString()
            priceProduct.text = getString(R.string.price, homeProductLayout?.price.toString())
            titleProduct.text = homeProductLayout?.title.toString()
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }

        return binding.root
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showEmpty() {
    }

    override fun showProducts(products: List<String>?) {
    }


    private fun initPresenter() {
        mHomePresenter = HomePresenterImp(this)
    }
}