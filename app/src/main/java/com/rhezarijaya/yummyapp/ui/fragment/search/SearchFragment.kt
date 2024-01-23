package com.rhezarijaya.yummyapp.ui.fragment.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.rhezarijaya.core.data.Resource
import com.rhezarijaya.core.ui.adapter.ItemFoodAdapter
import com.rhezarijaya.core.util.handleHttpException
import com.rhezarijaya.yummyapp.databinding.FragmentSearchBinding
import com.rhezarijaya.yummyapp.ui.activity.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity()
        val itemFoodAdapter = ItemFoodAdapter {
            startActivity(
                Intent(activity, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.EXTRA_FOOD_ID_KEY, it.id)
                }
            )
        }

        binding.rvFoodList.apply {
            adapter = itemFoodAdapter
            layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText?.isBlank() == true) {
                    binding.tvNoItem.isVisible = false
                    itemFoodAdapter.submitList(listOf())
                }

                viewModel.setSearchQuery(newText ?: "")

                return true
            }
        })

        viewModel.searchResult.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it is Resource.Loading

            when (it) {
                is Resource.Success -> {
                    if (_binding?.searchView?.query?.isNotBlank() == true) {
                        _binding?.tvNoItem?.isVisible = it.data?.isEmpty() == true
                        itemFoodAdapter.submitList(it.data)
                    }
                }

                is Resource.Loading -> {}

                is Resource.Error -> {
                    Toast.makeText(
                        activity,
                        it.exception.handleHttpException(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}