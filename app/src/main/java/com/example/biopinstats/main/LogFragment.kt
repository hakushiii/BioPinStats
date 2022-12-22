package com.example.biopinstats.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.RecyclerView
import com.example.biopinstats.MainApp
import com.example.biopinstats.authentication.AuthViewModel
import com.example.biopinstats.authentication.AuthViewModelFactory
import com.example.biopinstats.databinding.FragmentLogBinding
import com.example.biopinstats.main.adapter.RecyclerViewAdapter
import kotlinx.coroutines.launch

class LogFragment : Fragment() {
    private val viewModel: AuthViewModel by activityViewModels {
        AuthViewModelFactory(
            (activity?.application as MainApp).database
                .userDao()
        )
    }

    private var _binding: FragmentLogBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerView
        val adapter = RecyclerViewAdapter()
        recyclerView.adapter = adapter

        lifecycle.coroutineScope.launch {
            viewModel.getAllUser().collect {
                adapter.submitList(it)
            }
        }
    }
}