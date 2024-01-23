package com.rhezarijaya.core.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rhezarijaya.core.R
import com.rhezarijaya.core.databinding.ItemFoodIngredientBinding
import com.rhezarijaya.core.domain.model.FoodIngredient
import com.rhezarijaya.core.util.getIngredientThumbnailImage

class ItemFoodIngredientAdapter :
    ListAdapter<FoodIngredient, ItemFoodIngredientAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemFoodIngredientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        val data = getItem(position)

        holder.itemView.context.apply {
            binding.tvName.text = data.name
            binding.tvMeasure.text = data.measure

            Glide.with(this)
                .load(data.getIngredientThumbnailImage())
                .error(R.drawable.ic_launcher_foreground)
                .into(binding.ivThumbnail)
        }
    }

    inner class ViewHolder(
        val binding: ItemFoodIngredientBinding
    ) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FoodIngredient>() {
            override fun areItemsTheSame(
                oldItem: FoodIngredient,
                newItem: FoodIngredient
            ): Boolean = oldItem.name == newItem.name

            override fun areContentsTheSame(
                oldItem: FoodIngredient,
                newItem: FoodIngredient
            ): Boolean = oldItem == newItem
        }
    }
}