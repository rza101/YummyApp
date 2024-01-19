package com.rhezarijaya.core.util

import com.rhezarijaya.core.data.source.remote.response.FoodCategoryItemResponse
import com.rhezarijaya.core.data.source.remote.response.FoodDetailItemResponse
import com.rhezarijaya.core.data.source.remote.response.FoodFilterItemResponse
import com.rhezarijaya.core.data.source.room.entity.FavoriteFoodEntity
import com.rhezarijaya.core.domain.model.Food
import com.rhezarijaya.core.domain.model.FoodCategory
import com.rhezarijaya.core.domain.model.FoodIngredient
import retrofit2.HttpException
import java.net.URI

// DATA MAPPER
fun FavoriteFoodEntity.toDomain() =
    Food(
        id = id,
        name = name,
        drinkAlternate = null,
        category = null,
        area = null,
        instructions = null,
        thumbnailImage = thumbnailImage,
        tags = null,
        youtubeUrl = null,
        ingredients = null,
        source = null,
        imageSource = null,
        creativeCommonsConfirmed = null,
        dateModified = null,
    )

fun FoodCategoryItemResponse.toDomain() =
    FoodCategory(
        id = id,
        name = name,
        description = description,
        thumbnailImage = thumbnailImage
    )

fun FoodDetailItemResponse.toDomain() =
    Food(
        id = id,
        name = name,
        drinkAlternate = drinkAlternate,
        category = category,
        area = area,
        instructions = instructions,
        thumbnailImage = thumbnailImage,
        tags = tags,
        youtubeUrl = youtubeUrl,
        ingredients = mutableListOf<FoodIngredient>().apply {
            if (ingredient1?.isNotBlank() == true && measure1?.isNotBlank() == true) {
                add(FoodIngredient(ingredient1, measure1))
            }

            if (ingredient2?.isNotBlank() == true && measure2?.isNotBlank() == true) {
                add(FoodIngredient(ingredient2, measure2))
            }

            if (ingredient3?.isNotBlank() == true && measure3?.isNotBlank() == true) {
                add(FoodIngredient(ingredient3, measure3))
            }

            if (ingredient4?.isNotBlank() == true && measure4?.isNotBlank() == true) {
                add(FoodIngredient(ingredient4, measure4))
            }

            if (ingredient5?.isNotBlank() == true && measure5?.isNotBlank() == true) {
                add(FoodIngredient(ingredient5, measure5))
            }

            if (ingredient6?.isNotBlank() == true && measure6?.isNotBlank() == true) {
                add(FoodIngredient(ingredient6, measure6))
            }

            if (ingredient7?.isNotBlank() == true && measure7?.isNotBlank() == true) {
                add(FoodIngredient(ingredient7, measure7))
            }

            if (ingredient8?.isNotBlank() == true && measure8?.isNotBlank() == true) {
                add(FoodIngredient(ingredient8, measure8))
            }

            if (ingredient9?.isNotBlank() == true && measure9?.isNotBlank() == true) {
                add(FoodIngredient(ingredient9, measure9))
            }

            if (ingredient10?.isNotBlank() == true && measure10?.isNotBlank() == true) {
                add(FoodIngredient(ingredient10, measure10))
            }

            if (ingredient11?.isNotBlank() == true && measure11?.isNotBlank() == true) {
                add(FoodIngredient(ingredient11, measure11))
            }

            if (ingredient12?.isNotBlank() == true && measure12?.isNotBlank() == true) {
                add(FoodIngredient(ingredient12, measure12))
            }

            if (ingredient13?.isNotBlank() == true && measure13?.isNotBlank() == true) {
                add(FoodIngredient(ingredient13, measure13))
            }

            if (ingredient14?.isNotBlank() == true && measure14?.isNotBlank() == true) {
                add(FoodIngredient(ingredient14, measure14))
            }

            if (ingredient15?.isNotBlank() == true && measure15?.isNotBlank() == true) {
                add(FoodIngredient(ingredient15, measure15))
            }

            if (ingredient16?.isNotBlank() == true && measure16?.isNotBlank() == true) {
                add(FoodIngredient(ingredient16, measure16))
            }

            if (ingredient17?.isNotBlank() == true && measure17?.isNotBlank() == true) {
                add(FoodIngredient(ingredient17, measure17))
            }

            if (ingredient18?.isNotBlank() == true && measure18?.isNotBlank() == true) {
                add(FoodIngredient(ingredient18, measure18))
            }

            if (ingredient19?.isNotBlank() == true && measure19?.isNotBlank() == true) {
                add(FoodIngredient(ingredient19, measure19))
            }

            if (ingredient20?.isNotBlank() == true && measure20?.isNotBlank() == true) {
                add(FoodIngredient(ingredient20, measure20))
            }
        },
        source = source,
        imageSource = imageSource,
        creativeCommonsConfirmed = creativeCommonsConfirmed,
        dateModified = dateModified,
    )

fun FoodFilterItemResponse.toDomain() =
    Food(
        id = id,
        name = name,
        drinkAlternate = null,
        category = null,
        area = null,
        instructions = null,
        thumbnailImage = thumbnailImage,
        tags = null,
        youtubeUrl = null,
        ingredients = null,
        source = null,
        imageSource = null,
        creativeCommonsConfirmed = null,
        dateModified = null,
    )

fun Food.toEntity() =
    FavoriteFoodEntity(
        id = id,
        name = name,
        thumbnailImage = thumbnailImage,
    )

// OTHERS
fun Exception?.handleHttpException(): String {
    this?.printStackTrace()

    return if (this is HttpException) {
        response()?.errorBody()?.string() ?: Constants.UNEXPECTED_ERROR
    } else {
        Constants.UNEXPECTED_ERROR
    }
}

fun FoodIngredient.getIngredientThumbnailImage(): String? =
    try {
        URI(
            "https",
            "www.themealdb.com",
            "/images/ingredients/$name.png",
            ""
        ).toString()
    } catch (e: Exception) {
        null
    }