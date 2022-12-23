package com.example.biopinstats.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.biopinstats.database.models.Log
import com.example.biopinstats.databinding.FragmentRecyclerViewItemBinding

class RecyclerViewAdapter(private var onUpdateClicked: (Log) -> Unit,
                          private var onDeleteClicked: (Log) -> Unit
): ListAdapter<Log, RecyclerViewAdapter.RecyclerViewViewHolder>(DiffCallback) {
    class RecyclerViewViewHolder(
        binding: FragmentRecyclerViewItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        val status = binding.status
        val uptime = binding.uptime
        val description = binding.description
        val updateButton = binding.updateButton
        val deleteButton = binding.deleteButton
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder {
        return RecyclerViewViewHolder(
            FragmentRecyclerViewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        val item = getItem(position)
        holder.status.text = item.Status
        holder.uptime.text = item.Uptime
        holder.description.text = item.Description

        holder.updateButton.setOnClickListener {
            onUpdateClicked(item)
        }
        holder.deleteButton.setOnClickListener {
            onDeleteClicked(item)
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Log>() {
            override fun areItemsTheSame(oldItem: Log, newItem: Log): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Log, newItem: Log): Boolean {
                return oldItem == newItem
            }
        }
    }
}