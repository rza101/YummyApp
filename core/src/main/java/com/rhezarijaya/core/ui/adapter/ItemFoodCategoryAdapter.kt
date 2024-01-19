package com.rhezarijaya.core.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rhezarijaya.core.R
import com.rhezarijaya.core.databinding.ItemFoodCategoryBinding
import com.rhezarijaya.core.domain.model.FoodCategory

class ItemFoodCategoryAdapter(
    private val onItemClick: (FoodCategory) -> Unit
) : ListAdapter<FoodCategory, ItemFoodCategoryAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemFoodCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        val binding: ItemFoodCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FoodCategory>() {
            override fun areItemsTheSame(oldItem: FoodCategory, newItem: FoodCategory): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: FoodCategory, newItem: FoodCategory): Boolean =
                oldItem == newItem
        }
    }
}