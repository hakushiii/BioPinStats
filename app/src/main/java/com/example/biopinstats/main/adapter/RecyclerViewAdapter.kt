package com.example.biopinstats.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.biopinstats.database.models.User
import com.example.biopinstats.databinding.FragmentRecyclerViewItemBinding

class RecyclerViewAdapter: ListAdapter<User, RecyclerViewAdapter.RecyclerViewViewHolder>(DiffCallback) {
    class RecyclerViewViewHolder(
        binding: FragmentRecyclerViewItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        val username = binding.username
        val password = binding.password
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
        holder.username.text = item.username
        holder.password.text = item.password
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.username == newItem.username
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }
}