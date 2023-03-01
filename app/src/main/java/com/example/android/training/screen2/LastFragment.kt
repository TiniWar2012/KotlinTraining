package com.example.android.training.screen2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android.training.databinding.FragmentHomeV2Binding
import com.example.android.training.home.presenter.HomePresenter
import com.example.android.training.home.presenter.HomePresenterImp
import com.example.android.training.home.presenter.HomeView

class LastFragment : Fragment(), HomeView {

    private var mHomePresenter: HomePresenter? = null

    private var _binding: FragmentHomeV2Binding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeV2Binding.inflate(inflater, container, false)

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