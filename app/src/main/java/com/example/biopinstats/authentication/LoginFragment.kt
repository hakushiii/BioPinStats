package com.example.biopinstats.authentication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.biopinstats.EnabledNavigationActivity
import com.example.biopinstats.MainApp
import com.example.biopinstats.R
import com.example.biopinstats.databinding.FragmentLoginBinding
import com.example.biopinstats.databinding.FragmentRegisterBinding


class LoginFragment : Fragment() {
    private val viewModel: AuthViewModel by activityViewModels {
        AuthViewModelFactory(
            (activity?.application as MainApp).database
                .userDao()
        )
    }

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerButton.setOnClickListener {
                view: View -> view.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.loginButton.setOnClickListener {
            login()
        }
    }

    private fun userExist(): Boolean {
        return viewModel.userExist(
            username = binding.username.text.toString(),
            password = binding.password.text.toString()
        )
    }

    private fun login() {
        if (userExist()) {
            startActivity(Intent(activity, EnabledNavigationActivity::class.java))
        }
        else {

        }

    }
}