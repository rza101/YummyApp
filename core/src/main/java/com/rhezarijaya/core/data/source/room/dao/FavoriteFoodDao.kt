package com.rhezarijaya.core.data.source.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rhezarijaya.core.data.source.room.entity.FavoriteFoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteFoodDao {
    @Query("SELECT * FROM favorite_foods ORDER BY name")
    fun getFavoriteFoods(): Flow<List<FavoriteFoodEntity>>

    @Query("SELECT * FROM favorite_foods WHERE id = :id")
    fun getFavoriteFoodById(id: String): Flow<FavoriteFoodEntity?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavoriteFood(favoriteFoodEntity: FavoriteFoodEntity)

    @Delete
    fun deleteFavoriteFood(favoriteFoodEntity: FavoriteFoodEntity)
}