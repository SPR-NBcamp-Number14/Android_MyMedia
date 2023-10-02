package com.example.android_mymedia.search

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android_mymedia.databinding.SearchItemCategoryBinding
import com.example.android_mymedia.home.data.model.ButtonModel

class CategoryAdapter(
    private val onClicked: (ButtonModel) -> Unit
) : ListAdapter<ButtonModel, CategoryAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<ButtonModel>() {
        override fun areItemsTheSame(oldItem: ButtonModel, newItem: ButtonModel): Boolean {
            return oldItem.category == newItem.category
        }

        override fun areContentsTheSame(oldItem: ButtonModel, newItem: ButtonModel): Boolean {
            return oldItem == newItem
        }

    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SearchItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false,
            ),
            onClicked
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)
    }

    class ViewHolder(
        private val binding: SearchItemCategoryBinding,
        private val onClicked: (ButtonModel) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ButtonModel) = with(binding) {

        }
    }
}