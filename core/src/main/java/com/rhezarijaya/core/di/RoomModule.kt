package com.rhezarijaya.core.di

import android.content.Context
import androidx.room.Room
import com.rhezarijaya.core.data.source.room.YummyAppDatabase
import com.rhezarijaya.core.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Provides
    @Singleton
    fun provideYummyAppDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            YummyAppDatabase::class.java,
            Constants.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideFavoriteFoodDao(yummyAppDatabase: YummyAppDatabase) =
        yummyAppDatabase.getFavoriteFoodDao()
}