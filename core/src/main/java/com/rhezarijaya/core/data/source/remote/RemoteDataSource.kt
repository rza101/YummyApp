package com.rhezarijaya.core.data.source.remote

import com.rhezarijaya.core.BuildConfig
import com.rhezarijaya.core.data.source.remote.response.FoodCategoryItemResponse
import com.rhezarijaya.core.data.source.remote.response.FoodDetailItemResponse
import com.rhezarijaya.core.data.source.remote.response.FoodFilterItemResponse
import com.rhezarijaya.core.data.source.remote.service.TheMealDbAPIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: TheMealDbAPIService) {
    fun getFoodCategories(): Flow<RemoteResult<List<FoodCategoryItemResponse>>> =
        flow {
            try {
                val foodCategories = apiService
                    .getFoodCategories(BuildConfig.THE_MEAL_DB_API_KEY)
                    .data

                if (foodCategories.isNotEmpty()) {
                    emit(RemoteResult.Success(foodCategories))
                } else {
                    emit(RemoteResult.Empty)
                }
            } catch (e: Exception) {
                emit(RemoteResult.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    fun getFoodsByCategory(category: String): Flow<RemoteResult<List<FoodFilterItemResponse>>> =
        flow {
            try {
                val foodItems = apiService
                    .getFoodsByCategory(BuildConfig.THE_MEAL_DB_API_KEY, category)
                    .data

                if (foodItems.isNotEmpty()) {
                    emit(RemoteResult.Success(foodItems))
                } else {
                    emit(RemoteResult.Empty)
                }
            } catch (e: Exception) {
                emit(RemoteResult.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    fun getFoodDetail(id: String): Flow<RemoteResult<FoodDetailItemResponse>> =
        flow {
            try {
                val foodDetail = apiService
                    .getFoodDetail(BuildConfig.THE_MEAL_DB_API_KEY, id)
                    .data

                if (foodDetail.isNotEmpty()) {
                    emit(RemoteResult.Success(foodDetail[0]))
                } else {
                    emit(RemoteResult.Empty)
                }
            } catch (e: Exception) {
                emit(RemoteResult.Error(e))
            }
        }.flowOn(Dispatchers.IO)
}