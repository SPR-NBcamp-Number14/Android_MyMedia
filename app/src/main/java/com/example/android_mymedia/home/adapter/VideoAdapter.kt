package com.example.android_mymedia.home.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android_mymedia.databinding.HomeVideoItemBinding
import com.example.android_mymedia.detail.DetailActivity
import com.example.android_mymedia.home.data.PlayListModel
import com.example.android_mymedia.unit.Unit.setViewCountFormat

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

            val viewCount = setViewCountFormat(item.viewCount)

            homeVideoItemIvThumbnail.load(item.mediumImgUrl)

            homeVideoItemTvTitle.text = item.title
            homeVideoItemChannelTitle.text = item.channelTitle
            homeVideoIteViewCount.text = "조회수 ${viewCount}회"
            homeVideoItemTvDatetime.text = item.publishAt

            itemView.setOnClickListener {
                Intent(context, DetailActivity::class.java).apply {
                    putExtra("data", item)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { context.startActivity(this) }
            }
        }
    }


}