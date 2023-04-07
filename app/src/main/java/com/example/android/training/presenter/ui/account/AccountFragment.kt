package com.example.android.training.presenter.ui.account

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.android.training.MainActivity
import com.example.android.training.MyPreference
import com.example.android.training.R
import com.example.android.training.databinding.FragmentAccountBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @Inject
    lateinit var myPreference: MyPreference

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)

        binding.btnChangeLanguage.apply {
            isChecked = myPreference.getLoginCountry() != "th"
            setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    val intent = Intent(activity, MainActivity::class.java)
                    myPreference.setLoginCountry("en")
                    startActivity(intent)
                    activity?.finish()
                } else {
                    val intent = Intent(activity, MainActivity::class.java)
                    myPreference.setLoginCountry("th")
                    startActivity(intent)
                    activity?.finish()
                }
            }
        }
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_accountFragment_to_loginFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnClose.apply {
            setOnClickListener {
                findNavController().navigate(R.id.action_accountFragment_to_HomeFragment)
            }
        }
    }
}