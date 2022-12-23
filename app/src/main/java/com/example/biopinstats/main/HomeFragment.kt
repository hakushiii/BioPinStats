package com.example.biopinstats.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.biopinstats.R
import com.example.biopinstats.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apiButton.setOnClickListener {
                view: View -> view.findNavController().navigate(R.id.action_homeFragment_to_liveChangesFragment)
        }
        binding.logButton.setOnClickListener {
                view: View -> view.findNavController().navigate(R.id.action_homeFragment_to_logFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.faq -> view?.findNavController()?.navigate(R.id.faqFragment)
            R.id.about -> view?.findNavController()?.navigate(R.id.aboutFragment)
        }
        return true
    }
}