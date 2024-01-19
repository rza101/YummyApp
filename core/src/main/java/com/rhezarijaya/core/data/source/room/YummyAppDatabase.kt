package com.rhezarijaya.core.data.source.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rhezarijaya.core.data.source.room.dao.FavoriteFoodDao
import com.rhezarijaya.core.data.source.room.entity.FavoriteFoodEntity

@Database(
    version = 1,
    entities = [FavoriteFoodEntity::class],
    exportSchema = false
)
abstract class YummyAppDatabase : RoomDatabase() {
    abstract fun getFavoriteFoodDao(): FavoriteFoodDao
}