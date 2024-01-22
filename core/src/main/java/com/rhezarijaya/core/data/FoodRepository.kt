package com.rhezarijaya.core.data

import com.rhezarijaya.core.data.source.remote.RemoteDataSource
import com.rhezarijaya.core.data.source.remote.RemoteResult
import com.rhezarijaya.core.data.source.room.RoomDataSource
import com.rhezarijaya.core.domain.model.Food
import com.rhezarijaya.core.domain.model.FoodCategory
import com.rhezarijaya.core.domain.repository.IFoodRepository
import com.rhezarijaya.core.util.Constants
import com.rhezarijaya.core.util.toDomain
import com.rhezarijaya.core.util.toEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class FoodRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val roomDataSource: RoomDataSource,
    private val databaseCoroutineScope: CoroutineScope,
) : IFoodRepository {
    override fun getFoodCategories(): Flow<Resource<List<FoodCategory>>> =
        flow {
            emit(Resource.Loading())

            when (val result = remoteDataSource.getFoodCategories().first()) {
                is RemoteResult.Success -> {
                    emit(Resource.Success(
                        result.data.map {
                            it.toDomain()
                        }
                    ))
                }

                RemoteResult.Empty -> {
                    emit(Resource.Success(listOf()))
                }

                is RemoteResult.Error -> {
                    emit(Resource.Error(result.exception))
                }
            }
        }

    override fun getFoodsByCategory(category: String): Flow<Resource<List<Food>>> =
        flow {
            emit(Resource.Loading())

            when (val result = remoteDataSource.getFoodsByCategory(category).first()) {
                is RemoteResult.Success -> {
                    emit(Resource.Success(
                        result.data.map {
                            it.toDomain()
                        }
                    ))
                }

                RemoteResult.Empty -> {
                    emit(Resource.Success(listOf()))
                }

                is RemoteResult.Error -> {
                    emit(Resource.Error(result.exception))
                }
            }
        }

    override fun searchFoodByName(searchQuery: String): Flow<Resource<List<Food>>> =
        flow {
            emit(Resource.Loading())

            when (val result = remoteDataSource.searchFoodByName(searchQuery).first()) {
                is RemoteResult.Success -> {
                    emit(Resource.Success(
                        result.data.map {
                            it.toDomain()
                        }
                    ))
                }

                RemoteResult.Empty -> {
                    emit(Resource.Success(listOf()))
                }

                is RemoteResult.Error -> {
                    emit(Resource.Error(result.exception))
                }
            }
        }

    override fun getFoodDetail(id: String): Flow<Resource<Food>> =
        flow {
            emit(Resource.Loading())

            when (val result = remoteDataSource.getFoodDetail(id).first()) {
                is RemoteResult.Success -> {
                    emit(Resource.Success(result.data.toDomain()))
                }

                RemoteResult.Empty -> {
                    emit(Resource.Error(Exception(Constants.UNEXPECTED_ERROR)))
                }

                is RemoteResult.Error -> {
                    emit(Resource.Error(result.exception))
                }
            }
        }

    override fun addFavoriteFood(food: Food) {
        databaseCoroutineScope.launch {
            roomDataSource.insertFavoriteFood(food.toEntity())
        }
    }

    override fun getFavoriteFoods(): Flow<List<Food>> =
        roomDataSource.getFavoriteFoods().map { list ->
            list.map {
                it.toDomain()
            }
        }

    override fun getFavoriteFoodById(id: String): Flow<Food?> =
        roomDataSource.getFavoriteFoodById(id).map {
            it?.toDomain()
        }

    override fun deleteFavoriteFood(food: Food) {
        databaseCoroutineScope.launch {
            roomDataSource.deleteFavoriteFood(food.toEntity())
        }
    }
}