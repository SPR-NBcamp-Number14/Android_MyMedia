package com.example.android_mymedia.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_mymedia.databinding.HomeCategoryItemBinding
import com.example.android_mymedia.home.data.PlayListModel

class CategoryAdapter(

) : ListAdapter<PlayListModel, CategoryAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<PlayListModel>() {
        override fun areItemsTheSame(oldItem: PlayListModel, newItem: PlayListModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PlayListModel, newItem: PlayListModel): Boolean {
            return oldItem == newItem
        }

    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            HomeCategoryItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(
        private val binding: HomeCategoryItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val context = binding.root.context
        fun bind(item: PlayListModel) = with(binding) {
            if (item.mediumImgUrl != null) {
                Glide.with(context)
                    .load(item.mediumImgUrl.toUri())
                    .into(homeCategoryItemIvThumbnail)
            }
        }
    }
}