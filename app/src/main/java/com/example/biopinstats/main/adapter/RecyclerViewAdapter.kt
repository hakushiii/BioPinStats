package com.example.biopinstats.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.biopinstats.database.models.Log
import com.example.biopinstats.databinding.FragmentRecyclerViewItemBinding

class RecyclerViewAdapter: ListAdapter<Log, RecyclerViewAdapter.RecyclerViewViewHolder>(DiffCallback) {
    class RecyclerViewViewHolder(
        binding: FragmentRecyclerViewItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        val time = binding.time
        val status = binding.status
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
        holder.time.text = item.Time
        holder.status.text = item.Status
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Log>() {
            override fun areItemsTheSame(oldItem: Log, newItem: Log): Boolean {
                return oldItem.Time == newItem.Time
            }

            override fun areContentsTheSame(oldItem: Log, newItem: Log): Boolean {
                return oldItem == newItem
            }
        }
    }
}