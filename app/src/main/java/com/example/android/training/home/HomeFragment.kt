package com.example.android.training.home

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.android.training.R
import com.example.android.training.home.model.HomeFilterLayout
import com.example.android.training.databinding.FragmentHomeBinding
import com.example.android.training.home.adapter.*
import com.example.android.training.home.controller.HomeController
import com.example.android.training.home.model.HomeBannerLayout
import com.example.android.training.home.model.HomeProductLayout
import com.example.android.training.home.presenter.application.ProductApplication
import me.relex.circleindicator.CircleIndicator3


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

class HomeFragment : Fragment(), HandleClickListener {

    //    private var _binding: FragmentFirstBinding? = null
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //recycler filter product
    private lateinit var recyclerFilter: RecyclerView
    private lateinit var filterList: ArrayList<HomeFilterLayout>
    private lateinit var filterAdapter: FilterAdapter

    //Image Slider (Banner)
    private lateinit var viewBanner: ViewPager2
    private lateinit var handler: Handler
    private lateinit var tabDots: CircleIndicator3
    private lateinit var apiBannerAdapter: HeaderBannerAdapter
    private lateinit var apiBannerList: List<HomeBannerLayout>

    // Bottom banner
    private lateinit var recyclerBannerBottom: RecyclerView
    private lateinit var apiBannerBottomAdapter: api_bottombanner_adapter

    // News
    private lateinit var recyclerNews: RecyclerView
    private lateinit var apiNewsAdapter: api_news_adapter

    //RecyclerView Product

    //New product
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var myAdapter: RecyclerView.Adapter<*>

    //best seller product
    private lateinit var recyclerView2: RecyclerView
    private lateinit var manager2: RecyclerView.LayoutManager
    private lateinit var myAdapter2: RecyclerView.Adapter<*>

    //recommend
    private lateinit var recyclerView3: RecyclerView
    private lateinit var myAdapter3: RecyclerView.Adapter<*>

    //grid product
    private lateinit var recycleView4: RecyclerView
    private lateinit var myAdapter4: RecyclerView.Adapter<*>

    //Data Member
    private val mViewModel by viewModels<HomeViewModel>()

    //
    lateinit var homeController: HomeController

    // Use the 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact
    // to share the ViewModel across fragments.
    private val homeViewModel: HomeViewModel by activityViewModels {
        HomeViewModelFactory(
            (activity?.application as ProductApplication).database
                .productDataDao()
        )
    }

    lateinit var product: HomeProductLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val imgUser =
            "https://img.icons8.com/external-tanah-basah-glyph-tanah-basah/48/1A1A1A/external-user-user-tanah-basah-glyph-tanah-basah-4.png"
        Glide.with(this).load(imgUser).error(R.mipmap.ic_launcher).fitCenter().into(binding.icoUser)
        val imgCart = "https://img.icons8.com/ios-filled/50/1A1A1A/shopping-bag.png"
        Glide.with(this).load(imgCart).error(R.mipmap.ic_launcher).fitCenter().into(binding.icoCart)
        homeViewModel.homeViewStateLiveData.observe(viewLifecycleOwner) {
            homeController.setData(it)
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeController = HomeController(this)
        binding.epoxyRecyclerView.adapter = homeController.adapter
        binding.epoxyRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(homeProductLayout: HomeProductLayout) {
        homeViewModel.updateProductItem(homeProductLayout)
    }
}

