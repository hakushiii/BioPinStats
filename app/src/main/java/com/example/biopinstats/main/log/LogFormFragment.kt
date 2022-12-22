package com.example.biopinstats.main.log

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.biopinstats.MainApp
import com.example.biopinstats.R
import com.example.biopinstats.database.LogViewModel
import com.example.biopinstats.database.LogViewModelFactory
import com.example.biopinstats.databinding.FragmentLogFormBinding
import kotlinx.coroutines.launch

class LogFormFragment : Fragment() {
    private val logViewModel: LogViewModel by activityViewModels {
        LogViewModelFactory(
            (activity?.application as MainApp).database
                .logDao()
        )
    }

    private var _binding: FragmentLogFormBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener { findNavController().navigateUp() }
        binding.submitButton.setOnClickListener { addLog() }
    }

    private fun addLog() {
        if (isEntryValid()) {
            logViewModel.addLog(
                time = binding.timeEdit.text.toString(),
                status = binding.statusEdit.text.toString()
            )
            findNavController().navigateUp()
        } else {
            //TODO
        }
    }

    private fun isEntryValid(): Boolean {
        return logViewModel.isEntryValid(
            Time = binding.timeEdit.text.toString(),
            Status = binding.timeEdit.text.toString()
        )
    }
}