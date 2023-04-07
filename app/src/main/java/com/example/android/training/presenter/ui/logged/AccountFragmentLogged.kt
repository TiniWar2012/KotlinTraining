package com.example.android.training.presenter.ui.logged

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
import com.example.android.training.databinding.FragmentAccountLoggedInBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AccountFragmentLogged : Fragment() {
    private var _binding: FragmentAccountLoggedInBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var myPreference: MyPreference

    private lateinit var auth: FirebaseAuth

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountLoggedInBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()
        val intent = Intent(activity, MainActivity::class.java)
        val displayname = intent.getStringExtra("name")
        binding.txtWelcomeGuest.welcome.text= displayname
        binding.btnLogout.setOnClickListener {
            LogOut()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnClose.apply {
            setOnClickListener {
                findNavController().navigate(R.id.action_loggedFragment_to_HomeFragment)
            }
        }
    }
    private var token = ""
    private fun LogOut(){
        myPreference.setLoginStatus(token)
        auth.signOut()
        startActivity(Intent(activity, MainActivity::class.java))
        activity?.finish()
    }
}