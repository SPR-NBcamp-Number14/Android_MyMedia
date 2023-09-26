package com.example.android_mymedia.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android_mymedia.R
import com.example.android_mymedia.databinding.SearchItemListBinding
import com.example.android_mymedia.home.data.PlayListModel

class SearchAdapter() : ListAdapter<PlayListModel, SearchAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<PlayListModel>() { //개념을 이해 해보면 좋을것
    override fun areItemsTheSame(oldItem: PlayListModel, newItem: PlayListModel): Boolean {
        return oldItem.datetime == newItem.datetime //나중에 id 로
    }

    override fun areContentsTheSame(oldItem: PlayListModel, newItem: PlayListModel): Boolean {
        return oldItem == newItem
    }

    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(SearchItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder( val binding: SearchItemListBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item: PlayListModel) = with(binding){
            searchImage.setImageResource(R.drawable.ic_launcher_background)
            searchTitle.text=item.title
        }
    }

}
