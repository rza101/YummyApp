package com.rhezarijaya.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodFilterItemResponse(
    @field:SerializedName("idMeal")
    val id: String,

    @field:SerializedName("strMeal")
    val name: String,

    @field:SerializedName("strMealThumb")
    val thumbnailImage: String?,
) : Parcelable