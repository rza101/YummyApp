package com.rhezarijaya.yummyapp.di

import com.rhezarijaya.core.domain.usecase.FoodUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {
    fun provideFoodUseCase(): FoodUseCase
}