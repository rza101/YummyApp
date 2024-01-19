package com.rhezarijaya.favorite.ui.fragment.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rhezarijaya.core.domain.usecase.FoodUseCase

class FavoriteViewModel(
    private val foodUseCase: FoodUseCase
) : ViewModel() {
    fun getFavoriteFoods() = foodUseCase.getFavoriteFoods().asLiveData()
}