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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Provides
    fun provideDatabaseCoroutineScope(): CoroutineScope = CoroutineScope(Dispatchers.IO)

    @Provides
    fun provideFavoriteFoodDao(yummyAppDatabase: YummyAppDatabase) =
        yummyAppDatabase.getFavoriteFoodDao()

    @Provides
    @Singleton
    fun provideYummyAppDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            YummyAppDatabase::class.java,
            Constants.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
}