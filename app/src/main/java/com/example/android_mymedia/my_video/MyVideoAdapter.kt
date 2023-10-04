package com.example.android_mymedia.my_video

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android_mymedia.databinding.MyVideoItemListBinding
import com.example.android_mymedia.detail.DetailActivity
import com.example.android_mymedia.home.data.model.toVideoEntity
import com.example.android_mymedia.room.VideoEntity

class MyVideoAdapter() : ListAdapter<VideoEntity, MyVideoAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<VideoEntity>() {
        override fun areItemsTheSame(oldItem: VideoEntity, newItem: VideoEntity): Boolean {
            return oldItem.videoUrl == newItem.videoUrl
        }

        override fun areContentsTheSame(oldItem: VideoEntity, newItem: VideoEntity): Boolean {
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
        private val context = binding.root.context
        fun bind(item: VideoEntity) = with(binding) {
            myVideoImage.load(item.mediumImgUrl)
            myVideoName.text = item.title

            itemView.setOnClickListener {
                Intent(context, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.EXTRA_DATA, item)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { context.startActivity(this) }
            }
        }
    }
}