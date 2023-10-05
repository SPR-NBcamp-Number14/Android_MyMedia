package com.example.android_mymedia.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_mymedia.databinding.SearchItemListBinding
import com.example.android_mymedia.search.searchdata.SearchListModel


class SearchAdapter(
    private val onItemClicked: (SearchListModel) -> Unit
) : ListAdapter<SearchListModel, SearchAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<SearchListModel>() { //개념을 이해 해보면 좋을것
        override fun areItemsTheSame(oldItem: SearchListModel, newItem: SearchListModel): Boolean {
            return oldItem.id == newItem.id //나중에 id 로
        }

        override fun areContentsTheSame(
            oldItem: SearchListModel,
            newItem: SearchListModel
        ): Boolean {
            return oldItem == newItem
        }

    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SearchItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onItemClicked
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(
        val binding: SearchItemListBinding,
        private val onItemClicked: (SearchListModel) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        private val context = binding.root.context
        fun bind(item: SearchListModel) = with(binding) {
            Glide.with(context)
                .load(item.defaultImgUrl.toUri())
                .into(searchImage)

            searchTitle.text = item.title
            searchId.text = item.description

            itemView.setOnClickListener {
                onItemClicked(item)
            }

        }
    }

}
