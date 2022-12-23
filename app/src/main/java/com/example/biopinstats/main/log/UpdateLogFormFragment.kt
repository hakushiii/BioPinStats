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
import com.example.biopinstats.database.LogViewModel
import com.example.biopinstats.database.LogViewModelFactory
import com.example.biopinstats.database.models.Log
import com.example.biopinstats.databinding.FragmentUpdateLogFormBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UpdateLogFormFragment : Fragment() {
    private val logViewModel: LogViewModel by activityViewModels {
        LogViewModelFactory(
            (activity?.application as MainApp).database
                .logDao()
        )
    }

    private val navigationArgs: UpdateLogFormFragmentArgs by navArgs()

    private var _binding: FragmentUpdateLogFormBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateLogFormBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = navigationArgs.id

        lifecycle.coroutineScope.launch {
            getLog(id).collect {
                binding.apply {
                    timeEdit.setText(it.Time)
                    statusEdit.setText(it.Status)
                }
            }
        }

        binding.backButton.setOnClickListener { findNavController().navigateUp() }
        binding.submitButton.setOnClickListener { updateLog(id) }
    }

    private fun updateLog(id: Int) {
        if (isEntryValid()) {
            logViewModel.updateLog(
                id = id,
                time = binding.timeEdit.text.toString(),
                status = binding.statusEdit.text.toString()
            )
            findNavController().navigateUp()
        } else {
            //TODO
        }
    }

    private fun getLog(id: Int): Flow<Log> = logViewModel.getLog(id)

    private fun isEntryValid(): Boolean {
        return logViewModel.isEntryValid(
            Time = binding.timeEdit.text.toString(),
            Status = binding.timeEdit.text.toString()
        )
    }
}