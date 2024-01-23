package com.rhezarijaya.yummyapp.ui.fragment.category

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rhezarijaya.core.data.Resource
import com.rhezarijaya.core.ui.adapter.ItemFoodCategoryAdapter
import com.rhezarijaya.core.util.Constants
import com.rhezarijaya.core.util.handleHttpException
import com.rhezarijaya.yummyapp.databinding.FragmentCategoryBinding
import com.rhezarijaya.yummyapp.ui.activity.foodlist.FoodListActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment() {
    private var _binding: FragmentCategoryBinding? = null
    private val binding: FragmentCategoryBinding
        get() = _binding!!

    private val viewModel: CategoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity()

        val itemFoodCategoryAdapter = ItemFoodCategoryAdapter {
            startActivity(
                Intent(activity, FoodListActivity::class.java).apply {
                    putExtra(FoodListActivity.EXTRA_CATEGORY_NAME_KEY, it.name)
                }
            )
        }

        binding.rvCategory.apply {
            adapter = itemFoodCategoryAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        binding.swipeRefreshLayout.apply {
            setDistanceToTriggerSync(Constants.SWIPE_REFRESH_LAYOUT_DISTANCE)
            setOnRefreshListener {
                isRefreshing = false
                loadData(activity, itemFoodCategoryAdapter)
            }
        }

        loadData(activity, itemFoodCategoryAdapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadData(context: Context, adapter: ItemFoodCategoryAdapter) {
        adapter.submitList(listOf())

        viewModel.getFoodCategories().observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it is Resource.Loading

            when (it) {
                is Resource.Success -> {
                    adapter.submitList(it.data)
                }

                is Resource.Loading -> {}

                is Resource.Error -> {
                    Toast.makeText(
                        context,
                        it.exception.handleHttpException(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}