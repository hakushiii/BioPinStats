package com.example.biopinstats.authentication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.biopinstats.MainApp
import com.example.biopinstats.R
import com.example.biopinstats.database.AuthViewModel
import com.example.biopinstats.database.AuthViewModelFactory
import com.example.biopinstats.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private val viewModel: AuthViewModel by activityViewModels {
        AuthViewModelFactory(
            (activity?.application as MainApp).database
                .userDao()
        )
    }

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.confirmButton.setOnClickListener {
            insertUser()
        }
        binding.cancelButton.setOnClickListener {
                view: View -> view.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

    private fun insertUser() {

        val usernameOriginal = binding.username.text.toString()
        val passwordOriginal = binding.password.text.toString()
        val passwordCheck = binding.confirmPassword.text.toString()

        if (usernameOriginal.isEmpty()) {
            binding.usernameLayout.error = "Cannot be Empty"
            binding.passwordLayout.error = null
            binding.confirmPasswordLayout.error = null
            Log.d("Register Fragment", "Username Empty")
        } else if (passwordOriginal.isEmpty()) {
            binding.usernameLayout.error = null
            binding.passwordLayout.error = "Cannot be Empty"
            binding.confirmPasswordLayout.error = null
            Log.d("Register Fragment","Password Empty")
        } else if (passwordCheck.isEmpty()) {
            binding.usernameLayout.error = null
            binding.passwordLayout.error = null
            binding.confirmPasswordLayout.error = "Cannot be Empty"
            Log.d("Register Fragment", "Check Password")
        } else {
            if (passwordOriginal == passwordCheck) {
                viewModel.newUser(
                    username = binding.username.text.toString(),
                    password = binding.password.text.toString()
                )
                binding.usernameLayout.error = null
                binding.passwordLayout.error = null
                binding.confirmPasswordLayout.error = null
                view?.findNavController()?.navigate(R.id.action_registerFragment_to_loginFragment)
            } else {
                binding.usernameLayout.error = null
                binding.passwordLayout.error = null
                binding.confirmPasswordLayout.error = "Password must match to confirm"
                Log.d("Register Fragment", "Password must Match")
            }
        }
    }
}