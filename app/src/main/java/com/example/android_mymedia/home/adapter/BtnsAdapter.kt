package com.example.android_mymedia.home.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android_mymedia.databinding.HomeBtnItemBinding
import com.example.android_mymedia.home.data.model.ButtonModel

class BtnsAdapter(
    private val onClicked: (ButtonModel) -> Unit
) : ListAdapter<ButtonModel, BtnsAdapter.ViewHolder>(
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
        private val binding: HomeBtnItemBinding,
        private val onClicked: (ButtonModel) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ButtonModel) = with(binding) {
            homeCategoryTvTitle.text = item.btnTitle

            homeCategoryTvTitle.setOnClickListener {
                homeCategoryTvTitle.isSelected = !homeCategoryTvTitle.isSelected
                if (homeCategoryTvTitle.isSelected) {
                    onClicked(item)
                    homeCategoryTvTitle.setTextColor(Color.GRAY)
                } else {
                    homeCategoryTvTitle.setTextColor(Color.WHITE)
                }

            }
        }
    }
}