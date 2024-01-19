package com.rhezarijaya.core.domain.model

data class Food(
    val id: String,
    val name: String,
    val drinkAlternate: String?,
    val category: String?,
    val area: String?,
    val instructions: String?,
    val thumbnailImage: String?,
    val tags: String?,
    val youtubeUrl: String?,
    val ingredients: List<FoodIngredient>?,
    val source: String?,
    val imageSource: String?,
    val creativeCommonsConfirmed: String?,
    val dateModified: String?,
)