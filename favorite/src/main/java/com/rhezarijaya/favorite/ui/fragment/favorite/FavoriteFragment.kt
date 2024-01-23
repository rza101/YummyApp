package com.rhezarijaya.favorite.ui.fragment.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.rhezarijaya.core.ui.adapter.ItemFoodAdapter
import com.rhezarijaya.favorite.databinding.FragmentFavoriteBinding
import com.rhezarijaya.favorite.di.DaggerFavoriteComponent
import com.rhezarijaya.favorite.ui.ViewModelFactory
import com.rhezarijaya.yummyapp.di.FavoriteModuleDependencies
import com.rhezarijaya.yummyapp.ui.activity.detail.DetailActivity
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _binding: FragmentFavoriteBinding? = null
    private val binding: FragmentFavoriteBinding
        get() = _binding!!

    private val viewModel: FavoriteViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerFavoriteComponent.builder()
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireContext(),
                    FavoriteModuleDependencies::class.java
                )
            )
            .context(requireContext())
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
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

        viewModel.getFavoriteFoods().observe(viewLifecycleOwner) {
            _binding?.tvNoItem?.isVisible = it.isEmpty()
            itemFoodAdapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}