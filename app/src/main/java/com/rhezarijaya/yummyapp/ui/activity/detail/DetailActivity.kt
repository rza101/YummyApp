package com.rhezarijaya.yummyapp.ui.activity.detail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.rhezarijaya.core.data.Resource
import com.rhezarijaya.core.ui.adapter.ItemFoodIngredientAdapter
import com.rhezarijaya.core.util.Helpers
import com.rhezarijaya.core.util.handleHttpException
import com.rhezarijaya.yummyapp.R
import com.rhezarijaya.yummyapp.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    private val viewModel: DetailViewModel by viewModels()
    private val itemFoodIngredientAdapter = ItemFoodIngredientAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        if (!intent.hasExtra(EXTRA_FOOD_ID_KEY)) {
            finish()
        } else {
            val foodId = intent.getStringExtra(EXTRA_FOOD_ID_KEY) ?: ""

            binding.rvIngredients.apply {
                adapter = itemFoodIngredientAdapter
                layoutManager = LinearLayoutManager(this@DetailActivity)
            }

            loadData(foodId)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun loadData(id: String) {
        binding.clContent.isVisible = false
        binding.fab.isVisible = false

        viewModel.getFoodDetail(id).observe(this) {
            binding.progressBar.isVisible = it is Resource.Loading

            when (it) {
                is Resource.Success -> {
                    it.data?.let { food ->
                        Glide.with(this)
                            .load(food.thumbnailImage)
                            .error(R.drawable.ic_launcher_foreground)
                            .into(binding.ivThumbnail)

                        binding.tvName.text = food.name
                        binding.tvArea.text = food.area ?: "-"
                        binding.tvInstructions.text = food.instructions ?: "-"
                        binding.tvYoutube.text = food.youtubeUrl ?: "-"

                        food.ingredients?.let { foodIngredients ->
                            itemFoodIngredientAdapter.submitList(foodIngredients)
                        }

                        if (Helpers.isFavoriteInstalled()) {
                            binding.fab.isVisible = true
                            viewModel.getFavoriteFoodById(id)
                                .observe(this@DetailActivity) { favoriteFood ->
                                    if (favoriteFood == null) {
                                        binding.fab.setOnClickListener {
                                            viewModel.addFavoriteFood(food)
                                        }
                                        binding.fab.setImageDrawable(
                                            ContextCompat.getDrawable(
                                                this,
                                                R.drawable.baseline_favorite_border_24
                                            )
                                        )
                                    } else {
                                        binding.fab.setOnClickListener {
                                            viewModel.deleteFavoriteFood(food)
                                        }
                                        binding.fab.setImageDrawable(
                                            ContextCompat.getDrawable(
                                                this,
                                                R.drawable.baseline_favorite_24
                                            )
                                        )
                                    }
                                }
                        }

                        binding.clContent.isVisible = true
                    }
                }

                is Resource.Loading -> {}

                is Resource.Error -> {
                    Toast.makeText(
                        this,
                        it.exception.handleHttpException(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    companion object {
        const val EXTRA_FOOD_ID_KEY = "EXTRA_FOOD_ID_KEY"
    }
}