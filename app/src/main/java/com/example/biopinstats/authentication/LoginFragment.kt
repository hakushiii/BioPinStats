package com.example.biopinstats.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.biopinstats.EnabledNavigationActivity
import com.example.biopinstats.MainApp
import com.example.biopinstats.R
import com.example.biopinstats.database.AuthViewModel
import com.example.biopinstats.database.AuthViewModelFactory
import com.example.biopinstats.databinding.FragmentLoginBinding


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
        val usernameOriginal = binding.username.text.toString()
        val passwordOriginal = binding.password.text.toString()

        if (usernameOriginal.isEmpty()) {
            binding.usernameLayout.error = "Cannot be Empty"
            binding.passwordLayout.error = null
            Log.d("Register Fragment", "Username Empty")
        } else if (passwordOriginal.isEmpty()) {
            binding.passwordLayout.error = "Cannot be Empty"
            binding.usernameLayout.error = null
            Log.d("Register Fragment","Password Empty")
        } else {
            if (userExist()) {
                startActivity(Intent(activity, EnabledNavigationActivity::class.java))
                binding.loginButton.error = null
                binding.usernameLayout.error = null
                binding.passwordLayout.error = null
                binding.username.setText("")
                binding.password.setText("")
            } else {
                binding.usernameLayout.error = "User not found"
                binding.passwordLayout.error = null
                Log.d("Login Fragment", "nuguseyo")
            }
        }
    }
}