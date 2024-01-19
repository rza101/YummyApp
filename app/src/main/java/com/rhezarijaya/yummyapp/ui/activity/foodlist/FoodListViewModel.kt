package com.rhezarijaya.yummyapp.ui.activity.foodlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rhezarijaya.core.domain.usecase.FoodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FoodListViewModel @Inject constructor(
    private val foodUseCase: FoodUseCase
) : ViewModel() {
    fun getFoodsByCategory(category: String) =
        foodUseCase.getFoodsByCategory(category).asLiveData()
}