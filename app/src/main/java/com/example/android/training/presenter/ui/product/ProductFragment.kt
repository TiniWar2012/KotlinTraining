package com.example.android.training.presenter.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android.training.databinding.FragmentProductBinding
import com.example.android.training.presenter.ui.product.adapter.ViewPagerAdapter
import com.example.android.training.presenter.ui.product.model.ProductLayout
import com.google.android.material.tabs.TabLayoutMediator

val tabArray = arrayOf(
    "Drink", "Product"
)

class ProductFragment : Fragment() {
    private var _binding: FragmentProductBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabArray[position]
        }.attach()
        return binding.root
    }
}
