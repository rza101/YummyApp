package com.rhezarijaya.core.domain.usecase

import com.rhezarijaya.core.data.Resource
import com.rhezarijaya.core.domain.model.Food
import com.rhezarijaya.core.domain.model.FoodCategory
import kotlinx.coroutines.flow.Flow

interface FoodUseCase {
    fun getFoodCategories(): Flow<Resource<List<FoodCategory>>>

    fun getFoodsByCategory(category: String): Flow<Resource<List<Food>>>

    fun searchFoodByName(searchQuery: String): Flow<Resource<List<Food>>>

    fun getFoodDetail(id: String): Flow<Resource<Food>>

    fun addFavoriteFood(food: Food)

    fun getFavoriteFoods(): Flow<List<Food>>

    fun getFavoriteFoodById(id: String): Flow<Food?>

    fun deleteFavoriteFood(food: Food)
}