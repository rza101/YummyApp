package com.rhezarijaya.core.data.source.room

import com.rhezarijaya.core.data.source.room.dao.FavoriteFoodDao
import com.rhezarijaya.core.data.source.room.entity.FavoriteFoodEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomDataSource @Inject constructor(private val favoriteFoodDao: FavoriteFoodDao) {
    fun getFavoriteFoods() =
        favoriteFoodDao.getFavoriteFoods()

    fun getFavoriteFoodById(id: String) =
        favoriteFoodDao.getFavoriteFoodById(id)

    fun insertFavoriteFood(favoriteFoodEntity: FavoriteFoodEntity) =
        favoriteFoodDao.insertFavoriteFood(favoriteFoodEntity)

    fun deleteFavoriteFood(favoriteFoodEntity: FavoriteFoodEntity) =
        favoriteFoodDao.deleteFavoriteFood(favoriteFoodEntity)
}