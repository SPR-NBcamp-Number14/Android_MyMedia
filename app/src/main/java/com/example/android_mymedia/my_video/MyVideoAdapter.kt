package com.example.android_mymedia.my_video

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android_mymedia.databinding.MyVideoItemListBinding
import com.example.android_mymedia.detail.DetailModel

class MyVideoAdapter() : ListAdapter<DetailModel, MyVideoAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<DetailModel>() {
        override fun areItemsTheSame(oldItem: DetailModel, newItem: DetailModel): Boolean {
            return oldItem.videoUrl == newItem.videoUrl
        }

        override fun areContentsTheSame(oldItem: DetailModel, newItem: DetailModel): Boolean {
            return oldItem == newItem
        }

    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVideoAdapter.ViewHolder {
        return ViewHolder(
            MyVideoItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyVideoAdapter.ViewHolder, position: Int) {

        val item = getItem(position)

        holder.bind(item)
    }

    class ViewHolder(
        private val binding: MyVideoItemListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item:DetailModel){

        }
    }
}