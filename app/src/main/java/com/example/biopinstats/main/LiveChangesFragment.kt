package com.example.biopinstats.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

        binding.commandStatus.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                when (s.toString()) {
                    "turn right" -> binding.commandImage.setImageResource(R.drawable.ic_baseline_turn_right_24)
                    "turn left" -> binding.commandImage.setImageResource(R.drawable.ic_baseline_turn_left_24)
                    "forward" -> binding.commandImage.setImageResource(R.drawable.ic_baseline_arrow_upward_24)
                    "backward" -> binding.commandImage.setImageResource(R.drawable.ic_baseline_arrow_downward_24)
                    "not moving" -> binding.commandImage.setImageResource(R.drawable.ic_baseline_trip_origin_24)
                }
            }
            override fun afterTextChanged(s: Editable?) {
                when (s.toString()) {
                    "turn right" -> binding.commandStatus.text = "TURN RIGHT"
                    "turn left" -> binding.commandStatus.text = "TURN LEFT"
                    "forward" -> binding.commandStatus.text = "FORWARD"
                    "backward" -> binding.commandStatus.text = "BACKWARD"
                    "not moving" -> binding.commandStatus.text = "IN PLACE"
                }
            }
        })
    }
}