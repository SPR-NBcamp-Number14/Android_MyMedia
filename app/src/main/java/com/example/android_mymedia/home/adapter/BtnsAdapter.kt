package com.example.android_mymedia.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android_mymedia.databinding.HomeBtnItemBinding
import com.example.android_mymedia.home.data.ButtonModel

class BtnsAdapter : ListAdapter<ButtonModel, BtnsAdapter.ViewHolder>(
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
            HomeBtnItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)
    }

    class ViewHolder(
        private val binding: HomeBtnItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ButtonModel) = with(binding) {
            homeCategoryTvTitle.text = item.btnTitle
        }
    }
}