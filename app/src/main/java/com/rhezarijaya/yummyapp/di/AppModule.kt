package com.rhezarijaya.yummyapp.di

import com.rhezarijaya.core.domain.usecase.FoodInteractor
import com.rhezarijaya.core.domain.usecase.FoodUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun provideFoodUseCase(foodInteractor: FoodInteractor): FoodUseCase
}