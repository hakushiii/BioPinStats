package com.example.biopinstats.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.biopinstats.MainApp
import com.example.biopinstats.R
import com.example.biopinstats.database.LogViewModel
import com.example.biopinstats.database.LogViewModelFactory
import com.example.biopinstats.databinding.FragmentLogBinding
import com.example.biopinstats.main.adapter.RecyclerViewAdapter
import kotlinx.coroutines.launch

class LogFragment : Fragment() {
    private val logViewModel: LogViewModel by activityViewModels {
        LogViewModelFactory(
            (activity?.application as MainApp).database
                .logDao()
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

        binding.formButton.setOnClickListener{
                view: View -> view.findNavController().navigate(R.id.action_logFragment_to_logFormFragment)
        }

        recyclerView = binding.recyclerView
        val adapter = RecyclerViewAdapter()
        recyclerView.adapter = adapter

        lifecycle.coroutineScope.launch {
            logViewModel.getAllLog().collect {
                adapter.submitList(it)
            }
        }
    }
}