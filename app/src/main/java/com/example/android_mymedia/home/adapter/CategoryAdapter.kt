package com.example.android_mymedia.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android_mymedia.databinding.HomeCategoryListBinding
import com.example.android_mymedia.home.data.PlayListModel

class CategoryAdapter(

) : ListAdapter<PlayListModel, CategoryAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<PlayListModel>() {
        override fun areItemsTheSame(oldItem: PlayListModel, newItem: PlayListModel): Boolean {
            return oldItem.datetime == newItem.datetime
        }

        override fun areContentsTheSame(oldItem: PlayListModel, newItem: PlayListModel): Boolean {
            return oldItem == newItem
        }

    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            HomeCategoryListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(
        private val binding: HomeCategoryListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PlayListModel) = with(binding) {

        }
    }
}