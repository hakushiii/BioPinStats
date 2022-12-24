package com.example.biopinstats.main.log

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.biopinstats.MainApp
import com.example.biopinstats.database.LogViewModel
import com.example.biopinstats.database.LogViewModelFactory
import com.example.biopinstats.databinding.FragmentCreateLogFormBinding

class CreateLogFormFragment : Fragment() {
    private val logViewModel: LogViewModel by activityViewModels {
        LogViewModelFactory(
            (activity?.application as MainApp).database
                .logDao()
        )
    }

    private var _binding: FragmentCreateLogFormBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateLogFormBinding.inflate(inflater, container, false)
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
                uptime = binding.timeEdit.text.toString(),
                status = binding.statusEdit.text.toString(),
                description = binding.descriptionEdit.text.toString()
            )
            findNavController().navigateUp()
            binding.statusLayout.error = null
        } else {
            binding.statusLayout.error = "Required Field"
        }
    }

    private fun isEntryValid(): Boolean {
        return logViewModel.isEntryValid(
            Status = binding.statusEdit.text.toString()
        )
    }
}