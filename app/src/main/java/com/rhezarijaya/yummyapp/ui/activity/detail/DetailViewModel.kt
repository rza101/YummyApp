package com.rhezarijaya.yummyapp.ui.activity.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rhezarijaya.core.domain.model.Food
import com.rhezarijaya.core.domain.usecase.FoodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val foodUseCase: FoodUseCase
) : ViewModel() {
    fun getFoodDetail(id: String) = foodUseCase.getFoodDetail(id).asLiveData()

    fun getFavoriteFoodById(id: String) = foodUseCase.getFavoriteFoodById(id).asLiveData()

    fun addFavoriteFood(food: Food) = foodUseCase.addFavoriteFood(food)

    fun deleteFavoriteFood(food: Food) = foodUseCase.deleteFavoriteFood(food)
}