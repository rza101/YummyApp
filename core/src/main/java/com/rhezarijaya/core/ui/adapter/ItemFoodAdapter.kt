package com.rhezarijaya.core.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rhezarijaya.core.R
import com.rhezarijaya.core.databinding.ItemFoodBinding
import com.rhezarijaya.core.domain.model.Food

class ItemFoodAdapter(
    private val onItemClick: (Food) -> Unit
) : ListAdapter<Food, ItemFoodAdapter.ViewHolder>(DIFF_CALLBACk) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        val data = getItem(position)

        holder.itemView.apply {
            context.apply {
                binding.tvName.text = data.name

                Glide.with(this)
                    .load(data.thumbnailImage)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(binding.ivThumbnail)
            }
            setOnClickListener {
                onItemClick(data)
            }
        }
    }

    class ViewHolder(
        val binding: ItemFoodBinding
    ) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private val DIFF_CALLBACk = object : DiffUtil.ItemCallback<Food>() {
            override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean =
                oldItem == newItem
        }
    }
}