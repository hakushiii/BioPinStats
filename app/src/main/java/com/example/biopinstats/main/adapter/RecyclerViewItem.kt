package com.example.biopinstats.main.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.biopinstats.databinding.FragmentRecyclerViewItemBinding

class RecyclerViewItem : Fragment() {

    private var _binding: FragmentRecyclerViewItemBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecyclerViewItemBinding.inflate(inflater, container, false)
        return binding.root
    }

}