package com.rhezarijaya.core.data.source.remote.service

import com.rhezarijaya.core.data.source.remote.response.FoodCategoriesResponse
import com.rhezarijaya.core.data.source.remote.response.FoodDetailResponse
import com.rhezarijaya.core.data.source.remote.response.FoodFilterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMealDbAPIService {
    @GET("{api_key}/categories.php")
    suspend fun getFoodCategories(
        @Path("api_key") apiKey: String
    ): FoodCategoriesResponse

    @GET("{api_key}/filter.php")
    suspend fun getFoodsByCategory(
        @Path("api_key") apiKey: String,
        @Query("c") category: String
    ): FoodFilterResponse

    @GET("{api_key}/search.php")
    suspend fun searchFoodByName(
        @Path("api_key") apiKey: String,
        @Query("s") searchQuery: String
    ): FoodFilterResponse

    @GET("{api_key}/lookup.php")
    suspend fun getFoodDetail(
        @Path("api_key") apiKey: String,
        @Query("i") id: String
    ): FoodDetailResponse
}