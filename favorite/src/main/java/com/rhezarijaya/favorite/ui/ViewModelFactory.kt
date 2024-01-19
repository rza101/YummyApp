package com.rhezarijaya.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rhezarijaya.core.domain.usecase.FoodUseCase
import com.rhezarijaya.favorite.ui.fragment.favorite.FavoriteViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val foodUseCase: FoodUseCase
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when {
        modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
            FavoriteViewModel(foodUseCase) as T
        }

        else -> throw IllegalArgumentException("Invalid viewmodel")
    }
}