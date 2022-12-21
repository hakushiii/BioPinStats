package com.example.biopinstats.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.biopinstats.R
import com.example.biopinstats.databinding.FragmentLiveChangesBinding
import com.example.biopinstats.main.api.ApiViewModel

class LiveChangesFragment : Fragment() {

    private val viewModel: ApiViewModel by viewModels()

    private var _binding: FragmentLiveChangesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLiveChangesBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}