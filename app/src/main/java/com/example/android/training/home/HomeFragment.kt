package com.example.android.training.home

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import me.relex.circleindicator.CircleIndicator3


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

class HomeFragment : Fragment() {

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
    private val homeViewModel by viewModels<HomeViewModel>()

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

//        homeController = HomeController()
//        binding.epoxyRecyclerView.adapter = homeController.adapter
//        binding.epoxyRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        homeViewModel.homeViewStateLiveData.observe(viewLifecycleOwner) {
            homeController.setData(it)
        }


        //Filter
//        filter()
//        //Banner Slider
//        getBanner()
//        getBannerBottom()
//        getNews()
//        getProductrcm()
//        manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        getAllData()
//        manager2 = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        getAllData2()
//        getProductGridView()
//        val controller = HeaderBannerController()
//        binding.epoxyRecyclerView.setControllerAndBuildModels(controller)
//
//        lifecycleScope.launchWhenStarted {
//            val response: Response<List<HomeBannerLayout>> = bannerService.getBanner()
//            val domainBanner: List<Banner> = response.body()!!.map {
//                bannerMapper.buildForm(headerbannerlayout = it)
//            }
//            controller.setData(domainBanner)
//        }

        return binding.root

    }


    //////

//    fun getAllData() {
//        Log.d("product fun", "start");
//        Api.retrofitService.getAllData().enqueue(object : Callback<List<product_model>> {
//            override fun onResponse(
//                call: Call<List<product_model>>,
//                response: Response<List<product_model>>
//            ) {
//                if (response.isSuccessful) {
//                    Log.d("api product", "start");
//                    recyclerView = binding.recyclerView.apply {
//                        myAdapter = product_adapter(response.body()!!)
//                        layoutManager = manager
//                        adapter = myAdapter
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<List<product_model>>, t: Throwable) {
//                t.printStackTrace()
//            }
//        })
//    }
//
//    fun getAllData2() {
//        Log.d("product 2 fun", "start");
//        Api.retrofitService.getAllData().enqueue(object : Callback<List<product_model>> {
//            override fun onResponse(
//                call: Call<List<product_model>>,
//                response: Response<List<product_model>>
//            ) {
//                if (response.isSuccessful) {
//                    Log.d("api product2 ", "start");
//                    recyclerView2 = binding.recyclerViewRecommend.apply {
//                        myAdapter2 = product_adapter(response.body()!!)
//                        layoutManager = manager2
//                        adapter = myAdapter2
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<List<product_model>>, t: Throwable) {
//                t.printStackTrace()
//            }
//        })
//    }
//
//    fun getBanner() {
//        Log.d("banner fun", "start");
//        banner_api.retrofitService.getBanner().enqueue(object : Callback<List<banner_model>> {
//            override fun onResponse(
//                call: Call<List<banner_model>>,
//                response: Response<List<banner_model>>
//            ) {
//                Log.d("api banner", "start");
//                viewBanner = binding.viewBanner
//                tabDots = binding.tabDots
//                handler = Handler(Looper.myLooper()!!)
//                apiBannerList = ArrayList(response.body())
//                apiBannerAdapter = api_banner_adapter(response.body()!!)
//                viewBanner.apply {
//                    adapter = apiBannerAdapter
//                    viewBanner.registerOnPageChangeCallback(object :
//                        ViewPager2.OnPageChangeCallback() {
//                        override fun onPageSelected(position: Int) {
//                            super.onPageSelected(position)
//                            handler.removeCallbacks(runnable)
//                            handler.postDelayed(runnable, 4000)
//                        }
//                    })
//                }
//                tabDots.setViewPager(viewBanner)
//                apiBannerAdapter.registerAdapterDataObserver(tabDots.adapterDataObserver);
//
//            }
//
//            override fun onFailure(call: Call<List<banner_model>>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//        })
//    }
//    //
//    fun getBannerBottom() {
//        Log.d("banner bot fun", "start");
//        banner_api.retrofitService.getBanner().enqueue(object : Callback<List<banner_model>> {
//            override fun onResponse(
//                call: Call<List<banner_model>>,
//                response: Response<List<banner_model>>
//            ) {
//                Log.d("api banner bot", "start");
//                recyclerBannerBottom = binding.recyclerViewBottombanner
//                apiBannerList = ArrayList(response.body())
//                apiBannerBottomAdapter = api_bottombanner_adapter(response.body()!!)
//                recyclerBannerBottom.apply {
//                    adapter = apiBannerBottomAdapter
//                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//                }
//            }
//
//            override fun onFailure(call: Call<List<banner_model>>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//        })
//    }
//    //
//    fun getNews() {
//        Log.d("call news fun", "start");
//        banner_api.retrofitService.getBanner().enqueue(object : Callback<List<banner_model>> {
//            override fun onResponse(
//                call: Call<List<banner_model>>,
//                response: Response<List<banner_model>>
//            ) {
//                Log.d("api news", "start");
//                recyclerNews = binding.recyclerViewNews
//                apiBannerList = ArrayList(response.body())
//                apiNewsAdapter = api_news_adapter(response.body()!!)
//                recyclerNews.apply {
//                    adapter = apiNewsAdapter
//                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//                }
//            }
//
//            override fun onFailure(call: Call<List<banner_model>>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//        })
//    }
//    //
//    fun getProductrcm(){
//        Log.d("recommendproduct", "start");
//        Api.retrofitService.getAllData().enqueue(object : Callback<List<product_model>> {
//            override fun onResponse(
//                call: Call<List<product_model>>,
//                response: Response<List<product_model>>
//            ) {
//                if (response.isSuccessful) {
//                    Log.d("api recommend product", "start");
//                    recyclerView3 = binding.recyclerViewBestpd.apply {
//                        myAdapter3 = product_adapter(response.body()!!)
//                        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//                        adapter = myAdapter3
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<List<product_model>>, t: Throwable) {
//                t.printStackTrace()
//            }
//        })
//    }
//    //
//    fun getProductGridView(){
//        Log.d("api grid ", "call");
//        product_gridview_api.retrofitService.getAllProducts().enqueue(object : Callback<List<Product>?> {
//            override fun onResponse(
//                call: Call<List<Product>?>,
//                response: Response<List<Product>?>
//            ) {
//                Log.d("api grid", "ready");
//                if(response.isSuccessful){
//                    Log.d("api grid", "start");
//                    recycleView4 = binding.gridProduct.apply {
//                        myAdapter4 = product2_adapter(response.body()!!)
//                        layoutManager = GridLayoutManager(context,2)
//                        adapter = myAdapter4
//                        Log.d("api grid","done" );
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<List<Product>?>, t: Throwable) {
//                t.printStackTrace()
//            }
//        })
//    }
//
//    private val runnable = Runnable {
//        val totalItem: Int = apiBannerList.size - 1
//        val currentItem: Int = viewBanner.currentItem
//        if (currentItem < totalItem) {
//            viewBanner.currentItem = viewBanner.currentItem + 1
//        } else {
//            viewBanner.currentItem = 0
//        }
//    }
//
//    //Filter
//    private fun filter() {
//        recyclerFilter = binding.recyclerFilterList
//        recyclerFilter.setHasFixedSize(true)
//        recyclerFilter.layoutManager =
//            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//
//        filterList = ArrayList()
//
//        filterList.add(filter_model("https://i.imgur.com/MTIhoBu.png", "Coupons"))
//        filterList.add(filter_model("https://i.imgur.com/KrVXY8q.png", "Shop by Brands"))
//        filterList.add(filter_model("https://i.imgur.com/GY0Oivg.png", "Shop by Store"))
//        filterList.add(filter_model("https://i.imgur.com/vY96MH8.png", "Shop by Product"))
//        filterList.add(filter_model("https://i.imgur.com/P8Wpf3u.png", "Beauty"))
//        filterList.add(filter_model("https://i.imgur.com/v4Oy7lA.png", "Women"))
//        filterList.add(filter_model("https://i.imgur.com/1E4dcgS.png", "Man"))
//        filterList.add(filter_model("https://i.imgur.com/OAIhcCj.png", "Kids"))
//        filterList.add(filter_model("https://i.imgur.com/DGTavMl.png", "Home"))
//        filterList.add(filter_model("https://i.imgur.com/8vp64Co.png", "Tech"))
//        filterList.add(filter_model("https://i.imgur.com/qlOiabU.png", "Sport - Travel -"))
//        filterList.add(filter_model("https://i.imgur.com/PLifqv5.png", "Gift"))
//
//        filterAdapter = filterList_adapter(filterList)
//        recyclerFilter.adapter = filterAdapter
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeController = HomeController()
        binding.epoxyRecyclerView.adapter = homeController.adapter
        binding.epoxyRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

