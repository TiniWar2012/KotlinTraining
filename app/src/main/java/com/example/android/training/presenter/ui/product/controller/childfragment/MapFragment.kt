package com.example.android.training.presenter.ui.product.controller.childfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android.training.databinding.ChildFragmentDrinkBinding

class MapFragment : Fragment() {
    private var _binding: ChildFragmentDrinkBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = ChildFragmentDrinkBinding.inflate(inflater, container, false)

        return binding.root
    }
}