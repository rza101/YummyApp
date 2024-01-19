package com.rhezarijaya.yummyapp.ui.fragment.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rhezarijaya.core.domain.usecase.FoodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val foodUseCase: FoodUseCase
) : ViewModel() {
    fun getFoodCategories() = foodUseCase.getFoodCategories().asLiveData()
}