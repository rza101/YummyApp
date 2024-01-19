package com.rhezarijaya.yummyapp.ui.activity.foodlist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.rhezarijaya.core.data.Resource
import com.rhezarijaya.core.ui.adapter.ItemFoodAdapter
import com.rhezarijaya.core.util.Constants
import com.rhezarijaya.core.util.handleHttpException
import com.rhezarijaya.yummyapp.databinding.ActivityFoodListBinding
import com.rhezarijaya.yummyapp.ui.activity.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFoodListBinding

    private val viewModel: FoodListViewModel by viewModels()
    private val itemFoodAdapter = ItemFoodAdapter {
        startActivity(
            Intent(this, DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_FOOD_ID_KEY, it.id)
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFoodListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!intent.hasExtra(EXTRA_CATEGORY_NAME_KEY)) {
            finish()
        } else {
            val category = intent.getStringExtra(EXTRA_CATEGORY_NAME_KEY) ?: ""

            supportActionBar?.apply {
                title = category
                setDisplayHomeAsUpEnabled(true)
            }

            binding.rvFoodList.apply {
                adapter = itemFoodAdapter
                layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
            }

            binding.swipeRefreshLayout.apply {
                setDistanceToTriggerSync(Constants.SWIPE_REFRESH_LAYOUT_DISTANCE)
                setOnRefreshListener {
                    isRefreshing = false
                    loadData(category)
                }
            }

            loadData(category)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun loadData(category: String) {
        itemFoodAdapter.submitList(listOf())

        viewModel.getFoodsByCategory(category).observe(this) {
            binding.progressBar.isVisible = it is Resource.Loading

            when (it) {
                is Resource.Success -> {
                    itemFoodAdapter.submitList(it.data)
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
        const val EXTRA_CATEGORY_NAME_KEY = "EXTRA_CATEGORY_NAME_KEY"
    }
}