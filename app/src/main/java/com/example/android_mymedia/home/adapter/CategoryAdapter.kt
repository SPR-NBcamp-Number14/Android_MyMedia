package com.example.android_mymedia.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.example.android_mymedia.databinding.HomeVideoItemBinding
import com.example.android_mymedia.home.data.PlayListModel

class VideoAdapter(

) : ListAdapter<PlayListModel, VideoAdapter.ViewHolder>(
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
            HomeVideoItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(
        private val binding: HomeVideoItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val context = binding.root.context
        fun bind(item: PlayListModel) = with(binding) {
            homeVideoItemIvThumbnail.load(item.mediumImgUrl)

            homeVideoItemTvTitle.text = item.title
            homeVideoItemChannelTitle.text = item.channelTitle
            homeVideoIteViewCount.text = "조회수 ${item.viewCount.toString()}회"
            homeVideoItemTvDatetime.text = item.publishAt
        }
    }
}