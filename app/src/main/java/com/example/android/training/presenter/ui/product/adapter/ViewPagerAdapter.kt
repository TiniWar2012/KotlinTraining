package com.example.android.training.presenter.ui.product.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.android.training.presenter.ui.product.childfragment.MapFragment
import com.example.android.training.presenter.ui.product.childfragment.ListProductFragment

private const val NUM_TABS = 2

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return MapFragment()
        }
        return ListProductFragment()
    }
}