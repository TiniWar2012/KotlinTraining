package com.example.android.training.presenter.ui.product.childfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android.training.databinding.ChildFragmentFastfoodBinding

class FastFoodFragment : Fragment() {
    private var _binding: ChildFragmentFastfoodBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = ChildFragmentFastfoodBinding.inflate(inflater, container, false)

        return binding.root
    }
}