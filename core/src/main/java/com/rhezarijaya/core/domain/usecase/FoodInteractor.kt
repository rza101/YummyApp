package com.rhezarijaya.core.domain.usecase

import com.rhezarijaya.core.data.Resource
import com.rhezarijaya.core.domain.model.Food
import com.rhezarijaya.core.domain.model.FoodCategory
import com.rhezarijaya.core.domain.repository.IFoodRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FoodInteractor @Inject constructor(
    private val foodRepository: IFoodRepository
) : FoodUseCase {
    override fun getFoodCategories(): Flow<Resource<List<FoodCategory>>> =
        foodRepository.getFoodCategories()

    override fun getFoodsByCategory(category: String): Flow<Resource<List<Food>>> =
        foodRepository.getFoodsByCategory(category)

    override fun searchFoodByName(searchQuery: String): Flow<Resource<List<Food>>> =
        foodRepository.searchFoodByName(searchQuery)

    override fun getFoodDetail(id: String): Flow<Resource<Food>> =
        foodRepository.getFoodDetail(id)

    override fun addFavoriteFood(food: Food) =
        foodRepository.addFavoriteFood(food)

    override fun getFavoriteFoods(): Flow<List<Food>> =
        foodRepository.getFavoriteFoods()

    override fun getFavoriteFoodById(id: String): Flow<Food?> =
        foodRepository.getFavoriteFoodById(id)

    override fun deleteFavoriteFood(food: Food) =
        foodRepository.deleteFavoriteFood(food)
}