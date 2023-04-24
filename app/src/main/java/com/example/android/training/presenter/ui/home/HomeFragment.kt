package com.example.android.training.presenter.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.android.training.MyPreference
import com.example.android.training.R
import com.example.android.training.databinding.FragmentHomeBinding
import com.example.android.training.presenter.ui.home.adapter.HandleClickListener
import com.example.android.training.presenter.ui.home.adapter.ItemClickListener
import com.example.android.training.presenter.ui.home.controller.HomeController
import com.example.android.training.presenter.ui.home.model.HomeProductLayout
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class HomeFragment : Fragment(), ItemClickListener, HandleClickListener {

    //    private var _binding: FragmentFirstBinding? = null
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //
    private lateinit var homeController: HomeController
    @Inject
    lateinit var myPreference: MyPreference

    // Use the 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact
    // to share the ViewModel across fragments.
    private val homeViewModel by viewModels<HomeViewModel>()

    lateinit var product: HomeProductLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val imgUser =
            "https://img.icons8.com/external-tanah-basah-glyph-tanah-basah/48/1A1A1A/external-user-user-tanah-basah-glyph-tanah-basah-4.png"
        Glide.with(this).load(imgUser).fitCenter().into(binding.icoUser)
        val imgCart = "https://img.icons8.com/ios-filled/50/1A1A1A/shopping-bag.png"
        Glide.with(this).load(imgCart).fitCenter().into(binding.icoCart)
        homeViewModel.homeViewStateLiveData.observe(viewLifecycleOwner) {
            homeController.setData(it)
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeController = HomeController(this, this)
        binding.epoxyRecyclerView.adapter = homeController.adapter
        binding.epoxyRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//        binding.btnDelete.setOnClickListener {
//            homeViewModel.deleteAllData()
//        }
//        buttonAccount = binding.icoUser
//        buttonAccount.setOnClickListener {
//            findNavController().navigate(R.id.action_HomeFragment_to_accountFragment)
//        }
        binding.icoUser.apply {
            val isSignIn = myPreference.getLoginStatus() != ""
            setOnClickListener {
                if (isSignIn) {
                    findNavController().navigate(R.id.action_HomeFragment_to_loggedFragment)
                } else {
                    findNavController().navigate(R.id.action_HomeFragment_to_accountFragment)
                }
            }
        }
        binding.icoCart.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_cartFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(homeProductLayout: HomeProductLayout) {
        homeViewModel.likeProductItem(homeProductLayout)
    }

    override fun onClickItem(homeProductLayout: HomeProductLayout) {
        homeViewModel.getDetailProduct((homeProductLayout))
        // get id
        val bundle = bundleOf("homeProductLayout" to homeProductLayout)
        findNavController().navigate(R.id.action_FirstFragment_to_DetailProductFragment, bundle)
    }
}
